package project.logic.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import project.auth.models.User;
import project.logic.models.Exercise;
import project.logic.models.Plan;

import java.util.List;

public interface IPlansRepository extends JpaRepository<Plan, Long> {
    List<Plan> getPlanByUser(User user);
    List<Plan> getPlanByUserOrderByDayAsc(User user);
    List<Plan> getPlanByUserAndDay(User user, java.sql.Date day);
    List<Plan> getPlanByDayGreaterThanEqualAndDayLessThanEqual(java.sql.Date start, java.sql.Date end);
    List<Plan> getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserAndExerciseOrderByDayAsc
            (java.sql.Date start, java.sql.Date end, User user, Exercise exercise);
    List<Plan> getPlanByDayGreaterThanEqualAndDayLessThanEqualAndUserOrderByDayAsc
            (java.sql.Date start, java.sql.Date end, User user);
}

