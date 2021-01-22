package project.logic.analyzers;

import org.springframework.stereotype.Component;
import project.logic.dto.analisator.*;
import project.logic.models.Plan;


import java.util.ArrayList;
import java.util.List;

@Component
public class PlanAnalyzer {

    public List<LiftVolumeDto> calculateLiftsVolume(List<Plan> liftsList)
    {

        List<LiftVolumeDto> volumeDtoList= new ArrayList<>();

        boolean flag = false;

        for (Plan lift: liftsList) {
            LiftVolumeDto currentLiftVolumeDto = new LiftVolumeDto(lift);
            flag = true;
            if (volumeDtoList.size()>0){
                if (volumeDtoList.get(volumeDtoList.size()-1).getDay().equals(currentLiftVolumeDto.getDay())){
                    int index=volumeDtoList.size()-1;
                    while(index>=0 && volumeDtoList.get(index).getDay().equals(currentLiftVolumeDto.getDay())){
                        if (volumeDtoList.get(index).getExercise().equals(currentLiftVolumeDto.getExercise())){
                            volumeDtoList.get(index).addVolume(currentLiftVolumeDto.getVolume());
                            flag = false;
                            break;
                        }
                        else {
                            index --;
                        }
                    }
                }
            }
            if (flag){
                volumeDtoList.add(currentLiftVolumeDto);
            }
        }
        return volumeDtoList;
    }
    public LiftVolumeToIntensity calculateLiftsVolumeToIntensity(List<Plan> liftsList){
        LiftVolumeToIntensity chartData = new LiftVolumeToIntensity();
        double maxVolume=0;
        double maxCurrEstimatedWeight=0;
        double maxWeight=0;
        if (liftsList.size()>0) {
            chartData.setExercise(liftsList.get(0).getExercise().getName());
        }
        else
            return chartData;

        for (Plan p: liftsList) {
            if(p.getRpe()>-1)
            {

            if(p.getWeight()>maxWeight)
                maxWeight=p.getWeight();

            if(!chartData.getDay().isEmpty() && p.getDay().equals(chartData.getDay().get(chartData.getDay().size()-1))) {
                if (p.getWeight()>chartData.getWeight().get(chartData.getWeight().size()-1)) {
                    chartData.getWeight().remove(chartData.getWeight().size()-1);
                    chartData.getWeight().add(p.getWeight());
                }
                if (p.getEstimatedMax()>maxCurrEstimatedWeight)
                    maxCurrEstimatedWeight=p.getEstimatedMax();
                if (p.getWeight()>chartData.getIntensity().get(chartData.getIntensity().size()-1)){
                    chartData.getIntensity().remove(chartData.getIntensity().size()-1);
                    chartData.getIntensity().add(p.getWeight());
                }
                double tmp = chartData.getVolume().get(chartData.getVolume().size()-1);
                chartData.getVolume().remove(chartData.getVolume().size()-1);
                chartData.getVolume().add(p.getVolume()+tmp);
            }
            else {

                if(!chartData.getIntensity().isEmpty()) {
                    double tmp = chartData.getIntensity().get(chartData.getIntensity().size() - 1);
                    chartData.getIntensity().remove(chartData.getIntensity().size() - 1);
                    chartData.getIntensity().add(tmp / maxCurrEstimatedWeight);
                    if (maxVolume < chartData.getVolume().get(chartData.getVolume().size() - 1)) {
                        maxVolume = chartData.getVolume().get(chartData.getVolume().size() - 1);
                    }
                }
                chartData.getDay().add(p.getDay());
                chartData.getVolume().add(p.getVolume());
                chartData.getIntensity().add(p.getWeight());
                chartData.getWeight().add(p.getWeight());
                maxCurrEstimatedWeight=p.getEstimatedMax();
            }
            }
        }
        if (chartData.getIntensity().size()>0) {
            double tmp = chartData.getIntensity().get(chartData.getIntensity().size() - 1);
            chartData.getIntensity().remove(chartData.getIntensity().size() - 1);
            chartData.getIntensity().add(tmp / maxCurrEstimatedWeight);

            if (maxVolume < chartData.getVolume().get(chartData.getVolume().size() - 1))
                maxVolume = chartData.getVolume().get(chartData.getVolume().size() - 1);
            //////////////////////////////////////////////////////////////////////////////////////////////
            List<Double> newChartVolume = new ArrayList<>();
            for (int i = 0; i < chartData.getVolume().size(); i++) {
                newChartVolume.add(chartData.getVolume().get(i) / maxVolume);
            }
            chartData.setVolume(newChartVolume);
            List<Double> newChartWeight = new ArrayList<>();
            for (int i = 0; i < chartData.getWeight().size(); i++) {
                newChartWeight.add(chartData.getWeight().get(i) / maxWeight);
            }
            chartData.setWeight(newChartWeight);
            chartData.setMaxWeight(maxWeight);
            chartData.setMaxVolume(maxVolume);
        }
        return chartData;
    }
    public VolumeForTypes calculateLiftsVolumeForTypes(List<Plan> LiftsList)
    {
        VolumeForTypes volumeForTypes = new VolumeForTypes();
        for (Plan plan:LiftsList){
            if (plan.getRpe()>-1)
            {
            switch(plan.getExercise().getType()) {
                case MAIN_LIFT:
                    volumeForTypes.addToMainLiftsVolume(plan.getVolume());
                    break;
                case SUPPORT_LIFT:
                    volumeForTypes.addToSupportLiftsVolume(plan.getVolume());
                    break;
                case ACCESSORY:
                    volumeForTypes.addToAccessoryLiftsVolume(plan.getVolume());
                    break;
            }
            }
        }
        volumeForTypes.calculateLiftsVolumeInPercent();
        return volumeForTypes;
    }
    public VolumeForTrainingMethods calculateVolumeForTrainingMethods(List<Plan> LiftsList){
        VolumeForTrainingMethods volume = new VolumeForTrainingMethods();
        double intensityTMP=0;
        double volumeTMP=0;
        for (Plan lift: LiftsList){
            if (lift.getRpe()>-1) {
                volumeTMP = lift.getVolume();
                intensityTMP = lift.getRpe() > 0 ? lift.getWeight() / lift.getEstimatedMax() : 0.6;
                volume.addToTotal(volumeTMP);
                int reps = lift.getReps();

                if (intensityTMP >= 0.9)
                    volume.addToMaximalStrength(volumeTMP);
                else if (intensityTMP >= 0.55 && intensityTMP <= 0.65)
                    volume.addToDynamic(volumeTMP);
                else if (intensityTMP < 0.9 && lift.getRpe() == 10)
                    volume.addToRepetition(volumeTMP);
                else if(intensityTMP >= 0.7 && lift.getRpe() != 10)
                    volume.addToSubmaximal(volumeTMP);

                if (intensityTMP >= 0.75 && intensityTMP <= 0.85)
                    volume.addToOptimalRange(volumeTMP);


            }
        }
        return volume;
    }
    public PrilepinsTableView prilepinsTableCalculator(List<Plan> LiftsList){
        PrilepinsTableView prilepinsTableView = new PrilepinsTableView();
        for (Plan lift:LiftsList) {
            if(lift.getRpe()>=0)
            {
                if (lift.getRpe() == 0){
                    prilepinsTableView.addToRepsFor55to65(lift.getReps() * lift.getSets());
                    if(lift.getReps()>6)
                        prilepinsTableView.addWarning("Too many reps for 55-65% range");
                }
                else if (lift.getIntensity()>= 0.9) {
                    prilepinsTableView.addToRepsForOver90(lift.getReps() * lift.getSets());
                    if(lift.getReps()>2)
                        prilepinsTableView.addWarning("Too many reps for 90% range");
                }
                else if (lift.getIntensity()< 0.9 && lift.getIntensity()>= 0.8){
                    prilepinsTableView.addToRepsFor80to90(lift.getReps() * lift.getSets());
                    if(lift.getReps()>4)
                        prilepinsTableView.addWarning("Too many reps for 90-80% range");
                    if(lift.getReps()<2)
                        prilepinsTableView.addWarning("Not enough reps for 90-80% range");
                }
                else if (lift.getIntensity()< 0.8 && lift.getIntensity()>= 0.7){
                    prilepinsTableView.addToRepsFor70to80(lift.getReps() * lift.getSets());
                    if(lift.getReps()>6)
                        prilepinsTableView.addWarning("Too many reps for 80-70% range");
                    if(lift.getReps()<3){
                        prilepinsTableView.addWarning("Not enough reps for 80-70% range");
                    }
                }
            }
        }
        prilepinsTableView.calculateIntensityPercent();
        prilepinsTableView.calculateRepsPercent();
        return prilepinsTableView;
    }
}
