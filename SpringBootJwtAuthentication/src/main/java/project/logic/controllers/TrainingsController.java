package project.logic.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.auth.security.jwt.JwtProvider;
import project.logic.dto.TrainingDto;
import project.logic.exceptions.ConflictException;
import project.logic.interfaces.services.ITrainingService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/trainings")
@PreAuthorize("hasRole('USER')")
@ResponseBody
public class TrainingsController {

    @Autowired
    private ITrainingService trainingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity getTrainings() throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(trainingService.getTraining()));
    }
    @RequestMapping("/user")
    @GetMapping
    public ResponseEntity getUserTrainings(@RequestHeader(name="Authorization") String token ) throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(objectMapper.writeValueAsString(trainingService.getTrainingByUserId(userId)));
    }

    @PostMapping
    public ResponseEntity addUserTrainings(@Valid @RequestBody TrainingDto trainingDto,
                                           @RequestHeader (name="Authorization") String token ) throws ConflictException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(trainingService.addTraining(trainingDto, userId));
    }
}

