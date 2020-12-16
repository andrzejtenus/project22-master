package project.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.auth.models.User;
import project.auth.repositories.UserRepository;
import project.logic.analyzers.PlanAnalyzer;
import project.logic.dto.analisator.*;
import project.logic.dto.PlanDto;
import project.logic.exceptions.ConflictException;
import project.logic.exceptions.NotFoundException;
import project.logic.interfaces.services.IPlansService;
import project.logic.models.Exercise;
import project.logic.models.Plan;
import project.logic.repositries.IExercisesRepository;
import project.logic.repositries.IPlansRepository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlansService implements IPlansService {

    @Autowired
    private IPlansRepository plansRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IExercisesRepository exercisesRepository;

    @Autowired
    private PlanAnalyzer planAnalyzer;

    @Override
    public List<PlanDto> getAllPlans() {
        return plansRepository.findAll().stream().map(PlanDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> getAllUserPlans(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        return plansRepository.getPlanByUserOrderByDayAsc(user).stream().map(PlanDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> getAllUserPlansByDay(Long id, java.sql.Date day) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        return plansRepository.getPlanByUserAndDay(user, day).stream().map(PlanDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> getAllUserPlansForCurrentWeak(Long id, java.sql.Date day) {
        return null;
    }

    @Override
    public PlanDto addPlanForUser(Long user_id, PlanDto planDto) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findByUserAndName(user,planDto.getExercise()).
                orElseThrow(()-> new NotFoundException("Exercise not found"));

        return new PlanDto(plansRepository.save(new Plan(planDto,user,exercise)));
    }
    @Override
    public List<PlanDto> gatPlansFormRange()
    {
        return plansRepository.getPlanByDayGreaterThanEqualAndDayLessThanEqual(Date.valueOf("2020-10-01"), Date.valueOf("2020-10-04")).stream().map(PlanDto::new)
               .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> gatPlansFormRangeByUserAndExercise(Long user_id,Long exercise_id, java.sql.Date start, java.sql.Date end)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findById(exercise_id).orElseThrow(()-> new NotFoundException("Exercise not found"));
        if(exercise.getUser().equals(user)) {
            return plansRepository.getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc(start,
                    end, user, exercise).stream().map(PlanDto::new)
                    .collect(Collectors.toList());
        }
        throw new NotFoundException("exercise not found");
    }
    @Override
    public List<LiftVolumeDto> getVolumeFormRangeByUserAndExercise(Long user_id, Long exercise_id, java.sql.Date start, java.sql.Date end)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findById(exercise_id).orElseThrow(()-> new NotFoundException("Exercise not found"));
        if(exercise.getUser().equals(user)) {
            List<Plan> planList = plansRepository
                    .getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc
                            (start, end, user, exercise);
            return planAnalyzer.calculateLiftsVolume(planList);
        }
        throw new NotFoundException("exercise not found");

    }

    @Override
    public List<LiftVolumeDto> getAllUserPlansVolume(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        return planAnalyzer.calculateLiftsVolume(plansRepository.getPlanByUserOrderByDayAsc(user));
    }
    @Override
    public LiftVolumeToIntensity getVolumeToIntensityFormRangeByUserAndExercise(Long user_id, Long exercise_id, Date start, Date end)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findById(exercise_id)
                .orElseThrow(()-> new NotFoundException("Exercise not found"));
        if(exercise.getUser().equals(user)) {
            List<Plan> planList = plansRepository
                    .getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc
                            (start, end, user, exercise);
            return planAnalyzer.calculateLiftsVolumeToIntensity(planList);
        }
        throw new NotFoundException("exercise not found");
    }
    @Override
    public VolumeForTypes getVolumesFormRangeByUser(Long user_id, Date start, Date end)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));

            List<Plan> planList = plansRepository
                    .getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserOrderByDayAsc
                            (start, end, user);
            return planAnalyzer.calculateLiftsVolumeForTypes(planList);
    }
    @Override
    public void deletePlan(Long id, Long user_id) throws ConflictException {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Plan plan = plansRepository.findById(id).orElseThrow(()-> new NotFoundException("Plan not found"));
        if (plan.getUser().equals(user))
            plansRepository.delete(plan);
        else
            throw new ConflictException("No Permission for that plan");
    }
    @Override
    public VolumeForTrainingMethods getVolumesForStrengthTypesFormRangeByUser(Long user_id, Date start, Date end, Long exercise_id)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findById(exercise_id)
                .orElseThrow(()-> new NotFoundException("Exercise not found"));
        if(exercise.getUser().equals(user)) {
            List<Plan> planList = plansRepository
                    .getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc
                            (start, end, user, exercise);
            return planAnalyzer.calculateVolumeForTrainingMethods(planList);
        }
        throw new NotFoundException("exercise not found");
    }
    @Override
    public PrilepinsTableView getPrilepinsTableFormRangeByUser(Long user_id, Date start, Date end, Long exercise_id)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findById(exercise_id)
                .orElseThrow(()-> new NotFoundException("Exercise not found"));
        if(exercise.getUser().equals(user)) {
            List<Plan> planList = plansRepository
                    .getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc
                            (start, end, user, exercise);
            return planAnalyzer.prilepinsTableCalculator(planList);
        }
        throw new NotFoundException("exercise not found");
    }
}
