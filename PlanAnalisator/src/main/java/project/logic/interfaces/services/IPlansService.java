package project.logic.interfaces.services;

import project.logic.dto.analisator.*;
import project.logic.dto.PlanDto;
import project.logic.exceptions.ConflictException;

import java.sql.Date;
import java.util.List;

public interface IPlansService {
    public abstract List<PlanDto> getAllPlans();
    public abstract List<PlanDto> getAllUserPlans(Long id);
    public abstract List<PlanDto> getAllUserPlansByDay(Long id, java.sql.Date day);
    public abstract List<PlanDto> getAllUserPlansForCurrentWeak(Long id, java.sql.Date day);
    public abstract PlanDto addPlanForUser(Long id, PlanDto planDto);

    List<PlanDto> gatPlansFormRange();

    List<PlanDto> gatPlansFormRangeByUserAndExercise(Long user_id, Long exercise_id
            , java.sql.Date start, java.sql.Date end);

    List<LiftVolumeDto> getVolumeFormRangeByUserAndExercise(Long user_id, Long exercise_id, java.sql.Date start, java.sql.Date end);

    List<LiftVolumeDto> getAllUserPlansVolume(Long id);

    LiftVolumeToIntensity getVolumeToIntensityFormRangeByUserAndExercise(Long user_id, Long exercise_id, Date start, Date end);

    VolumeForTypes getVolumesFormRangeByUser(Long user_id, Date start, Date end);

    void deletePlan(Long id, Long user_id) throws ConflictException;

    VolumeForTrainingMethods getVolumesForStrengthTypesFormRangeByUser(Long user_id, Date start, Date end, Long exercise_id);


    PrilepinsTableView getPrilepinsTableFormRangeByUser(Long user_id, Date start, Date end, Long exercise_id);
}
