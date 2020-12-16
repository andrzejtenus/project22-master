package project.logic.dto.analisator;

import lombok.Getter;

@Getter
public class VolumeForTypes {
    double mainLiftsVolume;
    double accessoryLiftsVolume;
    double supportLiftsVolume;
    double mainLiftsVolumeInPercent;
    double accessoryLiftsVolumeInPercent;
    double supportLiftsVolumeInPercent;

    public void calculateLiftsVolumeInPercent(){
        double totalVolume = mainLiftsVolume + supportLiftsVolume + accessoryLiftsVolume;
        mainLiftsVolumeInPercent = mainLiftsVolume/totalVolume;
        supportLiftsVolumeInPercent= supportLiftsVolume/totalVolume;
        accessoryLiftsVolumeInPercent= accessoryLiftsVolume/totalVolume;
    }

    public void addToMainLiftsVolume(double liftVolume)
    {
        this.mainLiftsVolume += liftVolume;
    }

    public void addToAccessoryLiftsVolume(double liftVolume)
    {
        this.accessoryLiftsVolume += liftVolume;
    }

    public void addToSupportLiftsVolume(double liftVolume)
    {
        this.supportLiftsVolume += liftVolume;
    }

    public VolumeForTypes() {
        mainLiftsVolume=
        accessoryLiftsVolume=
        supportLiftsVolume=
        mainLiftsVolumeInPercent=
         accessoryLiftsVolumeInPercent=
         supportLiftsVolumeInPercent=0;
    }

    public VolumeForTypes(double mainLiftsVolume, double accessoryLiftsVolume, double supportLiftsVolume, double mainLiftsVolumeInPercent, double accessoryLiftsVolumeInPercent, double supportLiftsVolumeInPercent) {
        this.mainLiftsVolume = mainLiftsVolume;
        this.accessoryLiftsVolume = accessoryLiftsVolume;
        this.supportLiftsVolume = supportLiftsVolume;
        this.mainLiftsVolumeInPercent = mainLiftsVolumeInPercent;
        this.accessoryLiftsVolumeInPercent = accessoryLiftsVolumeInPercent;
        this.supportLiftsVolumeInPercent = supportLiftsVolumeInPercent;
    }

}
