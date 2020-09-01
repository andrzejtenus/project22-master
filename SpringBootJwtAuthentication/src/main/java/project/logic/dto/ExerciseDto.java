package project.logic.dto;

import project.logic.models.Exercise;
import project.logic.models.ExerciseTypes;

import javax.validation.constraints.NotNull;

public class ExerciseDto {


    private Long id;

    @NotNull(message = "Set exercise name")
    private String name;

    @NotNull(message = "Set exercise type")
    private ExerciseTypes type;

    private Long user;

    public ExerciseDto(Exercise exercise) {
        this.name = exercise.getName();
        this.type=exercise.getType();
        this.user=exercise.getUser().getId();
    }
}
