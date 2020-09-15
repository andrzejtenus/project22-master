package project.logic.interfaces.services;

import project.logic.dto.PlanDto;

import java.util.List;

public interface IPlansService {
    public abstract List<PlanDto> getAllPlans();
    public abstract List<PlanDto> getAllUserPlans(Long id);
    public abstract List<PlanDto> getAllUserPlansByDay(Long id, java.sql.Timestamp day);
    public abstract List<PlanDto> getAllUserPlansForCurrentWeak(Long id, java.sql.Timestamp day);
    public abstract PlanDto addPlanForUser(Long id, PlanDto planDto);
}
