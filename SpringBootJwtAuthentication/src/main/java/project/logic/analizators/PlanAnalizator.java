package project.logic.analizators;

import org.springframework.beans.factory.annotation.Autowired;
import project.auth.model.User;
import project.logic.dto.PlanDto;
import project.logic.interfaces.services.IPlansService;
import project.logic.models.Exercise;


import java.util.List;

public class PlanAnalizator {

    public double calculateMailLiftsVolume(List<PlanDto> liftsList)
    {
        return 1;
    }
}
