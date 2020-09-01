package project.logic.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import project.logic.models.Exercise;

public interface IExercisesRepository extends JpaRepository<Exercise, Long> {
}
