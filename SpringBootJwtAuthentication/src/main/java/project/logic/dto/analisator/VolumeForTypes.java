package project.logic.dto.analisator;

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

    public double getMainLiftsVolume() {
        return mainLiftsVolume;
    }

    public void setMainLiftsVolume(double mainLiftsVolume) {
        this.mainLiftsVolume = mainLiftsVolume;
    }

    public double getAccessoryLiftsVolume() {
        return accessoryLiftsVolume;
    }

    public void setAccessoryLiftsVolume(double accessoryLiftsVolume) {
        this.accessoryLiftsVolume = accessoryLiftsVolume;
    }

    public double getSupportLiftsVolume() {
        return supportLiftsVolume;
    }

    public void setSupportLiftsVolume(double supportLiftsVolume) {
        this.supportLiftsVolume = supportLiftsVolume;
    }

    public double getMainLiftsVolumeInPercent() {
        return mainLiftsVolumeInPercent;
    }

    public void setMainLiftsVolumeInPercent(double mainLiftsVolumeInPercent) {
        this.mainLiftsVolumeInPercent = mainLiftsVolumeInPercent;
    }

    public double getAccessoryLiftsVolumeInPercent() {
        return accessoryLiftsVolumeInPercent;
    }

    public void setAccessoryLiftsVolumeInPercent(double accessoryLiftsVolumeInPercent) {
        this.accessoryLiftsVolumeInPercent = accessoryLiftsVolumeInPercent;
    }

    public double getSupportLiftsVolumeInPercent() {
        return supportLiftsVolumeInPercent;
    }

    public void setSupportLiftsVolumeInPercent(double supportLiftsVolumeInPercent) {
        this.supportLiftsVolumeInPercent = supportLiftsVolumeInPercent;
    }
}
