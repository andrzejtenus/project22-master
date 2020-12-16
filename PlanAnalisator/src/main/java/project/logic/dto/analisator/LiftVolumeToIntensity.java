package project.logic.dto.analisator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LiftVolumeToIntensity {
    private List<Date> day;

    private String exercise;

    private List<java.lang.Double> volume;

    private List<java.lang.Double> intensity;

    private List<java.lang.Double> weight;

    private double maxVolume;

    private double maxWeight;

    public LiftVolumeToIntensity() {
        day = new ArrayList<Date>();
        volume = new ArrayList<Double>();
        intensity= new ArrayList<Double>();
        weight= new ArrayList<Double>();
    }

    public LiftVolumeToIntensity(List<Date> day, String exercise, List<Double> volume
            , List<Double> intensity,  List<Double> weight, double maxWeight, double maxVolume) {
        this.day = day;
        this.exercise = exercise;
        this.volume = volume;
        this.intensity = intensity;
        this.weight = weight;
        this.maxVolume=maxVolume;
        this.maxWeight=maxWeight;
    }

    public List<Double> getWeight() {
        return weight;
    }

    public void setWeight(List<Double> weight) {
        this.weight = weight;
    }


    public double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Date> getDay() {
        return day;
    }

    public void setDay(List<Date> day) {
        this.day = day;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public List<Double> getVolume() {
        return volume;
    }

    public void setVolume(List<Double> volume) {
        this.volume = volume;
    }

    public List<Double> getIntensity() {
        return intensity;
    }

    public void setIntensity(List<Double> intensity) {
        this.intensity = intensity;
    }
}
