package project.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.auth.model.User;
import project.auth.repository.UserRepository;
import project.logic.dto.ExerciseDto;
import project.logic.dto.PlanDto;
import project.logic.exceptions.NotFoundException;
import project.logic.interfaces.services.IPlansService;
import project.logic.models.Exercise;
import project.logic.models.Plan;
import project.logic.repositries.IExercisesRepository;
import project.logic.repositries.IPlansRepository;

import javax.xml.crypto.Data;
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

    @Override
    public List<PlanDto> getAllPlans() {
        return plansRepository.findAll().stream().map(PlanDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> getAllUserPlans(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        return plansRepository.getPlanByUser(user).stream().map(PlanDto::new)
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
    public List<PlanDto> gatPlansFormRangeByUserAndExercise(Long user_id, ExerciseDto exerciseDto)
    {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findByUserAndId(user,exerciseDto.getId()).
                orElseThrow(()-> new NotFoundException("Exercise not found"));
        return plansRepository.getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc(Date.valueOf("2020-10-01"),
                Date.valueOf("2020-10-04"), user, exercise).stream().map(PlanDto::new)
                .collect(Collectors.toList());
    }
}
