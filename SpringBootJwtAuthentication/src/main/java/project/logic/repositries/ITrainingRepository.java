package project.logic.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import project.auth.model.User;
import project.logic.models.Exercise;
import project.logic.models.Training;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ITrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findByUserId(Long user_id);
    Optional<Training> findByUserAndExerciseAndSet(User user, Exercise exercise, java.lang.Integer _set);
}
