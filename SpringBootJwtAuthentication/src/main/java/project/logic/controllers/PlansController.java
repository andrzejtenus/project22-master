package project.logic.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.auth.security.jwt.JwtProvider;
import project.logic.dto.PlanDto;
import project.logic.dto.TrainingDto;
import project.logic.exceptions.ConflictException;
import project.logic.interfaces.services.IPlansService;

import project.logic.repositries.IPlansRepository;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@RequestMapping("/api/plans")
public class PlansController {
    @Autowired
    private IPlansService plansService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity getPlans() throws JsonProcessingException {
        return ResponseEntity.ok(objectMapper.writeValueAsString(plansService.getAllPlans()));
    }

    @RequestMapping("/user")
    @GetMapping
    public ResponseEntity getUserPlans(@RequestHeader (name="Authorization") String token)
            throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(objectMapper.writeValueAsString(plansService.getAllUserPlans(userId)));
    }


    @PostMapping
    public ResponseEntity addUserPlan(@Valid @RequestBody PlanDto planDto,
                                           @RequestHeader (name="Authorization") String token ){
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(plansService.addPlanForUser(userId, planDto));
    }
    @RequestMapping("/user/{day}")
    @GetMapping
    public ResponseEntity getUserPlansByDay(@PathVariable(value = "day") Timestamp day, @RequestHeader (name="Authorization") String token)
            throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(objectMapper.writeValueAsString(plansService.getAllUserPlansByDay(userId, day)));
    }


}
