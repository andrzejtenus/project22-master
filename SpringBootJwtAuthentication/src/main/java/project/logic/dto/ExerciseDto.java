package project.logic.dto;

import project.logic.models.Exercise;
import project.logic.models.ExerciseTypes;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ExerciseDto {


    private Long id;

    @NotBlank(message = "Set exercise name")
    private String name;

    @NotBlank(message = "Set exercise type")
    private String type;

    private Long user;

    public ExerciseDto(Exercise exercise) {
        this.id=exercise.getId();
        this.name = exercise.getName();
        this.type=exercise.getType().toString();
        this.user=exercise.getUser().getId();
    }
    public ExerciseDto() {
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getUser() {
        return user;
    }


}
