package com.vue_test.controller;

import com.vue_test.entity.DashboardStats;
import com.vue_test.entity.DashboardTraffic;
import com.vue_test.entity.DashboardUserActivity;
import com.vue_test.repository.DashboardStatsRepository;
import com.vue_test.repository.DashboardTrafficRepository;
import com.vue_test.repository.DashboardUserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardTrafficRepository trafficRepository;
    private final DashboardStatsRepository statsRepository;
    private final DashboardUserActivityRepository userActivityRepository;

    @GetMapping("/traffic")
    public List<DashboardTraffic> getTrafficData() {
        return trafficRepository.findTop7ByOrderByRecordDateDesc();
    }

    @GetMapping("/stats")
    public List<DashboardStats> getStatsData() {
        return statsRepository.findAll();
    }

    @GetMapping("/user-activity")
    public List<DashboardUserActivity> getUserActivity() {
        return userActivityRepository.findAll();
    }

    @GetMapping("/summary")
    public Map<String, Object> getDashboardSummary() {
        return Map.of(
            "traffic", trafficRepository.findTop7ByOrderByRecordDateDesc(),
            "stats", statsRepository.findAll(),
            "users", userActivityRepository.findAll()
        );
    }
}
