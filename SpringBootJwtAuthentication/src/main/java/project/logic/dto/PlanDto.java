package project.logic.dto;

import project.logic.models.Plan;
import project.logic.models.Training;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class PlanDto {
    private Long id;

    private Long user;

    @NotNull(message = "Exercise can not be empty")
    private String exercise;

    @NotNull(message = "Day can not be empty")
    private java.sql.Timestamp day;

    @NotNull(message = "Weight can not be empty")
    private java.lang.Integer weight;

    @NotNull(message = "Sets can not be empty")
    private java.lang.Integer sets;

    @NotNull(message = "Reps can not be empty")
    private java.lang.Integer reps;

    @NotNull(message = "RPE can not be empty")
    private java.lang.Integer rpe;

    public PlanDto(Plan plan) {
        this.id = plan.getId();
        this.user = plan.getUser().getId();
        this.exercise = plan.getExercise().getName();
        this.day = plan.getDay();
        this.weight = plan.getWeight();
        this.sets = plan.getSets();
        this.reps = plan.getReps();
        this.rpe=plan.getRpe();
    }

    public PlanDto() {
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
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
}
