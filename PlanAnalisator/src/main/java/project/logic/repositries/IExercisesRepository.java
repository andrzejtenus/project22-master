package project.logic.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import project.auth.models.User;
import project.logic.models.Exercise;
import project.logic.models.ExerciseTypes;

import java.util.List;
import java.util.Optional;

public interface IExercisesRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByUserAndNameAndType(User user, String name, ExerciseTypes type);
    List<Exercise> findByUserAndType(User user, ExerciseTypes type);
    List<Exercise> findByUser(User user);
    Optional<Exercise> findByUserAndName(User user, String name);
    Optional<Exercise> findByUserAndId(User user, long id);

}

