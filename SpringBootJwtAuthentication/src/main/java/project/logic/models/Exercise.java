package project.logic.models;


import project.auth.model.User;
import project.logic.dto.ExerciseDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseTypes type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable=false)
    private User user;

    public String getName() {
        return name;
    }

    public ExerciseTypes getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public Exercise() {
    }

    public Exercise(ExerciseDto exerciseDto, User user) {
        this.name=exerciseDto.getName();
        this.type=ExerciseTypes.valueOf(exerciseDto.getType());
        this.user=user;
    }
}
