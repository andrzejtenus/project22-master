package project.logic.models;


import lombok.NonNull;
import project.auth.model.User;
import project.logic.dto.TrainingDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name="Trainings")
public class Training {
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
    @Column(nullable = false, name = "_set")
    private java.lang.Integer set;

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

    public Training(TrainingDto trainingDto, Exercise exercise, User user) {
        this.user = user;
        this.exercise = exercise;
        this.day = trainingDto.getDay();
        this.weight = trainingDto.getWeight();
        this.set = trainingDto.getSet();
        this.reps = trainingDto.getReps();
        this.rpe=trainingDto.getRpe();
        calculateEstimatedMax();
        calculateVolume();

    }

    private void calculateEstimatedMax()
    {
        this.estimatedMax=(this.weight * (this.reps+(10-this.rpe)))/30 + weight;
    }
    private void calculateVolume()
    {
        this.volume= this.weight * this.set * this.reps;
    }

    public Training() {
    }

    public Integer getRpe() {
        return rpe;
    }

    public void setRpe(Integer rpe) {
        this.rpe = rpe;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setSet(Integer set) {
        this.set = set;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Exercise getExercise() {
        return exercise;
    }


    public Double getWeight() {
        return weight;
    }

    public Integer getSet() {
        return set;
    }

    public Integer getReps() {
        return reps;
    }
}
