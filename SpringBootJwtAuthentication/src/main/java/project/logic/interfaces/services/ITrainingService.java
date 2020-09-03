package project.logic.interfaces.services;

import project.logic.dto.TrainingDto;
import project.logic.exceptions.ConflictException;

import java.util.List;

public interface ITrainingService {
    public abstract List<TrainingDto> getTraining();
    public abstract List<TrainingDto> getTrainingByUserId(Long id);
    public abstract List<TrainingDto> getTrainingByUserIdAndDate(Long id, java.sql.Timestamp day);
    public abstract TrainingDto addTraining(TrainingDto trainingDto, Long user_id ) throws ConflictException;
}
