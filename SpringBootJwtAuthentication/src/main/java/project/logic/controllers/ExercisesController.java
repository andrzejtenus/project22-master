package project.logic.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.auth.security.jwt.JwtProvider;
import project.logic.dto.ExerciseDto;
import project.logic.exceptions.ConflictException;
import project.logic.interfaces.services.IExerciseService;
import project.logic.models.ExerciseTypes;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exercises")
//@PreAuthorize("hasRole('USER')")
@ResponseBody
public class ExercisesController {
    @Autowired
    private IExerciseService exerciseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity getExercises() throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(exerciseService.getExercises()));
    }
    @RequestMapping("/user")
    @GetMapping
    public ResponseEntity getUserExercises(@RequestHeader (name="Authorization") String token ) throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(objectMapper.writeValueAsString(exerciseService.getExercisesByUserId(userId)));
    }
    @RequestMapping("/user/type/{type}")
    @GetMapping
    public ResponseEntity getUserExercisesByType(@RequestHeader (name="Authorization") String token,
                                                 @PathVariable("type") final ExerciseTypes type) throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(objectMapper.writeValueAsString(exerciseService
                .getExercisesByUserIdAndType(userId, type)));
    }
    @PostMapping
    public ResponseEntity addUserExercise(@Valid @RequestBody ExerciseDto exerciseDto,
                                      @RequestHeader (name="Authorization") String token ) throws ConflictException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(exerciseService.addExercise(exerciseDto, userId));
    }
    @RequestMapping("/user/{id}")
    @DeleteMapping
    public ResponseEntity deleteExercise(@RequestHeader (name="Authorization") String token,
                                         @PathVariable("id") final Long id){
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        exerciseService.deleteExercise(id, userId);
        return ResponseEntity.ok("exercise has been deleted");
    }


}
