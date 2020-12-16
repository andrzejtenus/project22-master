package project.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.auth.models.User;
import project.auth.repositories.UserRepository;
import project.logic.dto.TrainingDto;
import project.logic.exceptions.ConflictException;
import project.logic.exceptions.NotFoundException;
import project.logic.interfaces.services.ITrainingService;
import project.logic.models.Exercise;
import project.logic.models.Training;
import project.logic.repositries.IExercisesRepository;
import project.logic.repositries.ITrainingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingService implements ITrainingService {
    @Autowired
    private ITrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IExercisesRepository exercisesRepository;

    @Override
    public List<TrainingDto> getTraining() {
        return trainingRepository.findAll().stream().map(TrainingDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> getTrainingByUserId(Long user_id) {
        return trainingRepository.findByUserId(user_id).stream().map(TrainingDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TrainingDto> getTrainingByUserIdAndDate(Long id, java.sql.Date day) {
        return null;
    }

    @Override
    public TrainingDto addTraining(TrainingDto trainingDto, Long user_id) throws ConflictException {
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        Exercise exercise = exercisesRepository.findByUserAndName(user,trainingDto.getExercise()).
                orElseThrow(()-> new NotFoundException("Exercise not found"));
        Optional<Training> training = trainingRepository.findByUserAndExerciseAndSet(user, exercise
                , trainingDto.getSet());
        if(training.isPresent())
        {
            throw new ConflictException("Set already exists");
        }
        return new TrainingDto(trainingRepository.save(new Training(trainingDto, exercise, user)));
    }
}
