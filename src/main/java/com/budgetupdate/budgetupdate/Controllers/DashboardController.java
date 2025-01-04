package com.budgetupdate.budgetupdate.Controllers;

import com.budgetupdate.budgetupdate.DTOs.DashboardDTO;
import com.budgetupdate.budgetupdate.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/{userId}/dashboard")
    public ResponseEntity<DashboardDTO> getUserDashboard(@PathVariable Long userId) {
        // Fetch user-specific dashboard data
        DashboardDTO dashboardData = dashboardService.getDashboardDataForUser(userId);

        if (dashboardData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(dashboardData);
    }
}
