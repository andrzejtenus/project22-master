package project.logic.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import project.auth.models.User;
import project.logic.dto.ExerciseDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Training> trainings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Plan> plans = new ArrayList<>();

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

    public Long getId() {   
        return id;
    }

    public Exercise(ExerciseDto exerciseDto, User user) {
        this.name=exerciseDto.getName();
        this.type=ExerciseTypes.valueOf(exerciseDto.getType());
        this.user=user;
    }
}
