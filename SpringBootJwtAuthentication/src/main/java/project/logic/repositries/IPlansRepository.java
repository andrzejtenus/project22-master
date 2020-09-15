package project.logic.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import project.logic.models.Plan;

public interface IPlansRepository extends JpaRepository<Plan, Long> {
}
