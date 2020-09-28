package project.logic.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import project.auth.model.User;
import project.logic.models.Plan;

import java.sql.Timestamp;
import java.util.List;

public interface IPlansRepository extends JpaRepository<Plan, Long> {
    List<Plan> getPlanByUser(User user);
    List<Plan> getPlanByUserAndDay(User user, java.sql.Date day);
}
