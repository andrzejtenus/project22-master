package project.logic.dto.analisator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolumeForTrainingMethods {
    Double volumeForMaximalStrength;
    Double volumeForRest;
    Double volumeForOptimalRange;
    Double volumeForDynamic;
    Double volumeForRepetitionsMethod;
    Double volumeForSubmaximalMethod;
    Double totalVolume;

    public VolumeForTrainingMethods() {
        volumeForMaximalStrength=0.0;
        volumeForRest =0.0;
        volumeForOptimalRange =0.0;
        volumeForDynamic=0.0;
        totalVolume=0.0;
        volumeForRepetitionsMethod=0.0;
        volumeForSubmaximalMethod=0.0;
    }
    public void addToTotal(double value)
    {
        this.totalVolume +=value;
    }
    public void addToMaximalStrength(double value){
        this.volumeForMaximalStrength +=value;
    }
    public void addToOptimalRange(double value){
        this.volumeForOptimalRange +=value;
    }
    public void addToRest(double value){
        this.volumeForRest +=value;
    }
    public void addToDynamic(double value){
        this.volumeForDynamic +=value;
    }
    public void addToRepetition(double value){
        this.volumeForRepetitionsMethod +=value;
    }
    public void addToSubmaximal(double value){
        this.volumeForSubmaximalMethod +=value;
    }
}
