package project.logic.models;


import lombok.NonNull;
import project.auth.model.User;
import project.logic.dto.TrainingDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private java.sql.Timestamp day;

    @NonNull
    @Column(nullable = false)
    private java.lang.Integer weight;

    @NonNull
    @Column(nullable = false, name = "_set")
    private java.lang.Integer set;

    @NonNull
    @Column(nullable = false)
    private java.lang.Integer reps;

    public Training(TrainingDto trainingDto, Exercise exercise, User user) {
        this.user = user;
        this.exercise = exercise;
        this.day = trainingDto.getDay();
        this.weight = trainingDto.getWeight();
        this.set = trainingDto.getSet();
        this.reps = trainingDto.getReps();
    }

    public Training() {
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

    public void setDay(Timestamp day) {
        this.day = day;
    }

    public void setWeight(Integer weight) {
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

    public Timestamp getDay() {
        return day;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getSet() {
        return set;
    }

    public Integer getReps() {
        return reps;
    }
}
