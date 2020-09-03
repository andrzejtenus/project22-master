package project.logic.interfaces.services;

import project.logic.dto.ExerciseDto;
import project.logic.exceptions.ConflictException;
import project.logic.models.Exercise;
import project.logic.models.ExerciseTypes;

import java.util.List;

public interface IExerciseService {
    public abstract List<ExerciseDto> getExercises();
    public abstract List<ExerciseDto> getExercisesByUserId(Long user_id);
    public abstract List<ExerciseDto> getExercisesByType(ExerciseTypes type);
    public abstract List<ExerciseDto> getExercisesByUserIdAndType(Long id, ExerciseTypes type);
    public abstract ExerciseDto addExercise(ExerciseDto exerciseDto, Long user_id ) throws ConflictException;
}
