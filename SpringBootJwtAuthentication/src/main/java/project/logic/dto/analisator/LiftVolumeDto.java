package project.logic.dto.analisator;

import project.logic.models.Plan;

import java.sql.Date;

public class LiftVolumeDto {
    private java.sql.Date day;

    private String exercise;

    private java.lang.Double volume;

    public LiftVolumeDto(Date day, String exercise, Double volume) {
        this.day = day;
        this.exercise = exercise;
        this.volume = volume;
    }

    public LiftVolumeDto(Plan object) {
        this.day = object.getDay();
        this.exercise = object.getExercise().getName();
        this.volume = object.getVolume();
    }

    public void addVolume(double _volume){
        this.volume += _volume;
    }

    public LiftVolumeDto() {
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
