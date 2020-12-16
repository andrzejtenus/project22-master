package project.logic.models;

import lombok.NonNull;
import project.auth.models.User;
import project.logic.dto.PlanDto;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable=false)
    private Exercise exercise;

    @NonNull
    @Column(nullable = false)
    private java.sql.Date day;

    @NonNull
    @Column(nullable = false)
    private java.lang.Double weight;

    @NonNull
    @Column(nullable = false, name = "sets")
    private java.lang.Integer sets;

    @NonNull
    @Column(nullable = false)
    private java.lang.Integer reps;

    @NonNull
    @Column(nullable = false)
    private java.lang.Integer rpe;

    @Column
    private java.lang.Double estimatedMax;

    @Column
    private java.lang.Double volume;

    public Plan(PlanDto planDto, User user, Exercise exercise) {
        this.user = user;
        this.exercise = exercise;
        this.day = planDto.getDay();
        this.weight = planDto.getWeight();
        this.sets = planDto.getSets();
        this.reps = planDto.getReps();
        this.rpe=planDto.getRpe();
        calculateEstimatedMax();
        calculateVolume();
    }

    private void calculateEstimatedMax()
    {
        if (this.rpe.equals(10) && this.reps.equals(1)){
            this.estimatedMax=this.weight;
        }
        else {
            this.estimatedMax=(this.weight * (this.reps+(10-this.rpe)))/30 + weight;
        }

    }

    public double getIntensity(){
        return this.getWeight()/this.getEstimatedMax();
    }

    private void calculateVolume()
    {
        this.volume= this.weight * this.sets * this.reps;
    }

    public Double getEstimatedMax() {
        return estimatedMax;
    }

    public void setEstimatedMax(Double estimatedMax) {
        this.estimatedMax = estimatedMax;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getRpe() {
        return rpe;
    }

    public void setRpe(Integer rpe) {
        this.rpe = rpe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Plan() {
    }
}
