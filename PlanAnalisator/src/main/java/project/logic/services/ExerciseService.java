package project.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.auth.models.User;
import project.auth.repositories.UserRepository;
import project.logic.dto.ExerciseDto;
import project.logic.exceptions.ConflictException;
import project.logic.exceptions.NotFoundException;
import project.logic.interfaces.services.IExerciseService;
import project.logic.models.Exercise;
import project.logic.models.ExerciseTypes;
import project.logic.repositries.IExercisesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService implements IExerciseService {

    @Autowired
    private IExercisesRepository exercisesRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ExerciseDto> getExercises() {
             return exercisesRepository.findAll().stream().map(ExerciseDto::new).collect(Collectors.toList());
    }
    @Override
    public List<ExerciseDto> getExercisesByUserId(Long user_id) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not fund"));

        return exercisesRepository.findByUser(user).stream().map(ExerciseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ExerciseDto> getExercisesByType(ExerciseTypes type) {

        return null;
    }

    @Override
    public List<ExerciseDto> getExercisesByUserIdAndType(Long user_id, ExerciseTypes type) {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not fund"));
        return exercisesRepository.findByUserAndType(user, type).stream()
                .map(ExerciseDto::new).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto addExercise(ExerciseDto exerciseDto, Long user_id) throws ConflictException {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not fund"));
        Optional<Exercise> exercise = exercisesRepository.findByUserAndNameAndType(user, exerciseDto.getName(),
                ExerciseTypes.valueOf(exerciseDto.getType()));
        if (exercise.isPresent())
            throw new ConflictException("Exercise already exists");
        return new ExerciseDto(exercisesRepository.save(new Exercise(exerciseDto, user)));
    }
    @Override
    public void deleteExercise(Long id, Long user_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not fund"));
        Exercise exercise = exercisesRepository.findByUserAndId(user, id)
                .orElseThrow(()-> new NotFoundException("Exercise not fund"));
        exercisesRepository.delete(exercise);
    }
}
