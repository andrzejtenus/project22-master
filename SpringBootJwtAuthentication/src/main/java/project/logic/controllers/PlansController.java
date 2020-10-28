package project.logic.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.auth.security.jwt.JwtProvider;
import project.logic.dto.PlanDto;
import project.logic.interfaces.services.IPlansService;
import javax.validation.Valid;
import java.sql.Date;

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
        return ResponseEntity.ok(plansService.getAllUserPlans(userId));
    }


    @PostMapping
    public ResponseEntity addUserPlan(@Valid @RequestBody PlanDto planDto,
                                           @RequestHeader (name="Authorization") String token ){
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(plansService.addPlanForUser(userId, planDto));
    }
    @RequestMapping("/user/{day}")
    @GetMapping
    public ResponseEntity getUserPlansByDay(@PathVariable(value = "day") java.sql.Date day, @RequestHeader (name="Authorization") String token)
            throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(objectMapper.writeValueAsString(plansService.getAllUserPlansByDay(userId, day)));
    }
    @RequestMapping("/test")
    @GetMapping
    public ResponseEntity gettest(@RequestHeader (name="Authorization") String token,
            @RequestParam Long id,@RequestParam Date start, @RequestParam Date end)
    {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(plansService.
                gatPlansFormRangeByUserAndExercise(userId, id, start, end));
    }
    @RequestMapping("/test2")
    @GetMapping
    public ResponseEntity gettest2(@RequestHeader (name="Authorization") String token,
                                  @RequestParam Long id,@RequestParam Date start, @RequestParam Date end)
    {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(plansService.
                getVolumeFormRangeByUserAndExercise(userId, id, start, end));
    }
    @RequestMapping("/user/volume")
    @GetMapping
    public ResponseEntity getUserPlansVolume(@RequestHeader (name="Authorization") String token)
            throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(plansService.getAllUserPlansVolume(userId));
    }
    @RequestMapping("/user/volume_to_intensity")
    @GetMapping
    public ResponseEntity getUserPlansVolumeToIntensity(@RequestHeader (name="Authorization") String token
            ,@RequestParam Long id,@RequestParam Date start, @RequestParam Date end)
            throws JsonProcessingException {
        Long userId = jwtProvider.getUserIdFromToken(token.replace("Bearer ",""));
        return ResponseEntity.ok(plansService.
                getVolumeToIntensityFormRangeByUserAndExercise(userId, id, start, end));
    }
}
