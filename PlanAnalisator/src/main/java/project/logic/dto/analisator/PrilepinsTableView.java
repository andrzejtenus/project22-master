package project.logic.dto.analisator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PrilepinsTableView {
    double minPercent;
    double maxPercent;
    double optPercent;
    int repsFor55to65;
    int repsFor70to80;
    int repsFor80to90;
    int repsForOver90;
    double percentFor55to65;
    double percentFor70to80;
    double percentFor80to90;
    double percentForOver90;
    List<String>warnings;
    public PrilepinsTableView() {
        this.minPercent = 0;
        this.maxPercent = 0;
        this.optPercent = 0;
        this.repsFor55to65 = 0;
        this.repsFor70to80 = 0;
        this.repsFor80to90 = 0;
        this.repsForOver90 = 0;
        this.percentFor55to65 = 0;
        this.percentFor70to80 = 0;
        this.percentFor80to90 = 0;
        this.percentForOver90 = 0;
        this.warnings=new ArrayList<>();
    }
    public void addToRepsFor55to65(int reps){
        this.repsFor55to65 +=reps;
    }
    public void addToRepsFor70to80(int reps){
        this.repsFor70to80 +=reps;
    }
    public void addToRepsFor80to90(int reps){
        this.repsFor80to90 +=reps;
    }
    public void addToRepsForOver90(int reps){
        this.repsForOver90 +=reps;
    }
    public void calculateRepsPercent(){
        this.optPercent = (repsFor55to65/24.0 + this.repsFor70to80/18.0
                + this.repsFor80to90/15.0 + this.repsForOver90/4.0)*100;
        this.maxPercent = (repsFor55to65/30.0 + this.repsFor70to80/24.0
                + this.repsFor80to90/20.0 + this.repsForOver90/10.0)*100;
        this.minPercent = (repsFor55to65/18.0 + this.repsFor70to80/12.0
                + this.repsFor80to90/10.0 + this.repsForOver90/1.0)*100;
    }
    public void calculateIntensityPercent(){
        int allReps= this.repsFor55to65+repsFor70to80+repsFor80to90+repsFor70to80;
        if(allReps>0) {
            this.percentForOver90 = (double)this.repsForOver90 / (double)allReps;
            this.percentFor80to90 = (double)this.repsFor80to90 / (double)allReps;
            this.percentFor70to80 = (double)this.repsFor70to80 / (double)allReps;
            this.percentFor55to65 = (double)this.repsFor55to65 / (double)allReps;
        }
    }
    public void addWarning(String warning_){
        this.warnings.add(warning_);
    }


}
