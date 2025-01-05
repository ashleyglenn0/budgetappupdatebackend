package com.budgetupdate.budgetupdate.Controllers;

import com.budgetupdate.budgetupdate.DTOs.ChallengeDTO;
import com.budgetupdate.budgetupdate.Security.CustomUserDetails;
import com.budgetupdate.budgetupdate.Services.ChallengeService;
import com.budgetupdate.budgetupdate.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/{userId}/challenges")
public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges(@PathVariable Long userId) {
        List<ChallengeDTO> challenges = challengeService.getAllChallengesForUser(userId);
        return ResponseEntity.ok(challenges);
    }

    @PostMapping("/{challengeId}/enroll")
    public ResponseEntity<?> enrollInChallenge(@PathVariable Long userId,
                                               @PathVariable Long challengeId,
                                               @AuthenticationPrincipal CustomUserDetails userDetails) {
        // Ensure the authenticated user matches the request
        if (!userId.equals(userDetails.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        // Logic to enroll the user in the challenge
        challengeService.enrollUserInChallenge(challengeId, userId);

        return ResponseEntity.ok("Enrolled successfully!");
    }
}

