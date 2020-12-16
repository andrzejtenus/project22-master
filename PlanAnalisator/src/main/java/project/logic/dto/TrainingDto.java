package project.logic.dto;

import project.logic.models.Training;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class TrainingDto {

    private Long id;

    private Long user;

    @NotNull(message = "Exercise can not be empty")
    private String exercise;

    @NotNull(message = "Day can not be empty")
    private java.sql.Date day;

    @NotNull(message = "Weight can not be empty")
    private java.lang.Double weight;

    @NotNull(message = "Start can not be empty")
    private java.lang.Integer set;

    @NotNull(message = "Reps can not be empty")
    private java.lang.Integer reps;

    @NotNull(message = "RPE can not be empty")
    @Min(0)
    @Max(10)
    private java.lang.Integer rpe;


    public TrainingDto(Training training) {
        this.id = training.getId();
        this.user = training.getUser().getId();
        this.exercise = training.getExercise().getName();
        this.day = training.getDay();
        this.weight = training.getWeight();
        this.set = training.getSet();
        this.reps = training.getReps();
        this.rpe = training.getRpe();
    }

    public Integer getRpe() {
        return rpe;
    }

    public void setRpe(Integer rpe) {
        this.rpe = rpe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
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

    public Long getUser() {
        return user;
    }

    public String getExercise() {
        return exercise;
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

    public Integer getSet() {
        return set;
    }

    public Integer getReps() {
        return reps;
    }

    public TrainingDto() {
    }
}
