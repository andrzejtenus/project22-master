package project.logic.models;

import lombok.NonNull;
import project.auth.model.User;
import project.logic.dto.PlanDto;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private java.sql.Timestamp day;

    @NonNull
    @Column(nullable = false)
    private java.lang.Integer weight;

    @NonNull
    @Column(nullable = false, name = "sets")
    private java.lang.Integer sets;

    @NonNull
    @Column(nullable = false)
    private java.lang.Integer reps;

    public Plan(PlanDto planDto, User user, Exercise exercise) {
        this.user = user;
        this.exercise = exercise;
        this.day = planDto.getDay();
        this.weight = planDto.getWeight();
        this.sets = planDto.getSets();
        this.reps = planDto.getReps();
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

    public Timestamp getDay() {
        return day;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
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
