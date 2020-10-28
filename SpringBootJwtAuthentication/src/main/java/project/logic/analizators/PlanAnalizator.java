package project.logic.analizators;

import org.springframework.stereotype.Component;
import project.logic.dto.analisator.LiftVolumeDto;
import project.logic.dto.analisator.LiftVolumeToIntensity;
import project.logic.models.Plan;


import java.util.ArrayList;
import java.util.List;


@Component
public class PlanAnalizator {

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
        if (liftsList.size()>0)
        {
            chartData.setExercise(liftsList.get(0).getExercise().getName());
        }
        else
            return chartData;

        for (Plan p: liftsList)
        {
            if(!chartData.getDay().isEmpty() && p.getDay().equals(chartData.getDay().get(chartData.getDay().size()-1)))
            {
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

                maxCurrEstimatedWeight=p.getEstimatedMax();
            }
        }
        double tmp= chartData.getIntensity().get(chartData.getIntensity().size()-1);
        chartData.getIntensity().remove(chartData.getIntensity().size()-1);
        chartData.getIntensity().add(tmp/maxCurrEstimatedWeight);

        //////////////////////////////////////////////////////////////////////////////////////////////
        List<Double> newChartVolume = new ArrayList<>();
        for(int i= 0;i< chartData.getVolume().size();i++ ){
            newChartVolume.add(chartData.getVolume().get(i)/maxVolume);
        }
        chartData.setVolume(newChartVolume);

        return chartData;
    }
}
