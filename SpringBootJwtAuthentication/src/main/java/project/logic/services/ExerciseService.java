package project.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.auth.model.User;
import project.auth.repository.UserRepository;
import project.logic.dto.ExerciseDto;
import project.logic.exceptions.ConflictException;
import project.logic.exceptions.NotFoundException;
import project.logic.interfaces.services.IExerciseService;
import project.logic.models.Exercise;
import project.logic.models.ExerciseTypes;
import project.logic.repositries.IExercisesRepository;

import java.nio.channels.NotYetBoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ExerciseService implements IExerciseService {

    @Autowired
    IExercisesRepository exercisesRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<ExerciseDto> getExercises() {
             return exercisesRepository.findAll().stream().map(ExerciseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ExerciseDto> getExercisesByUserId(Long id) {
        return null;
    }

    @Override
    public List<ExerciseDto> getExercisesByType(ExerciseTypes type) {
        return null;
    }

    @Override
    public List<ExerciseDto> getExercisesByUserIdAndType(Long id, ExerciseTypes type) {
        return null;
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
}
