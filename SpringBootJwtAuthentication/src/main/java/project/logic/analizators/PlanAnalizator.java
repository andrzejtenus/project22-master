package project.logic.analizators;

import org.springframework.beans.factory.annotation.Autowired;
import project.logic.models.Plan;
import project.logic.repositries.IPlansRepository;

import java.util.List;

public class PlanAnalizator {
    @Autowired
    private IPlansRepository plansRepository;

    public double calculateMailLiftsVolume(java.sql.Timestamp start, java.sql.Timestamp end)
    {
        return 1;
    }
}
