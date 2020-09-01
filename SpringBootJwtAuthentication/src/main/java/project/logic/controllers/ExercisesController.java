package project.logic.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.auth.security.jwt.JwtProvider;
import project.logic.dto.ExerciseDto;
import project.logic.exceptions.ConflictException;
import project.logic.services.ExerciseService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exercises")
@PreAuthorize("hasRole('USER')")
@ResponseBody
public class ExercisesController {
    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity getExercises() throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(exerciseService.getExercises()));
    }
    @PostMapping
    public ResponseEntity addExercise(@Valid @RequestBody ExerciseDto exerciseDto,
                                      @RequestHeader (name="Authorization") String token ) throws ConflictException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(exerciseService.addExercise(exerciseDto, userId));
    }
}
