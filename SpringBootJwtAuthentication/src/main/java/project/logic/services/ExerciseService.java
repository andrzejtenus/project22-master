package project.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.logic.dto.ExerciseDto;
import project.logic.interfaces.services.IExerciseService;
import project.logic.models.ExerciseTypes;
import project.logic.repositries.IExercisesRepository;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ExerciseService implements IExerciseService {

    @Autowired
    IExercisesRepository exercisesRepository;

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
    public ExerciseDto addExercise(ExerciseDto exerciseDto, Long user_id) {
        return null;
    }
}
