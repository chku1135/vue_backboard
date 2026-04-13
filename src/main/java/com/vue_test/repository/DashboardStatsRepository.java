package com.vue_test.repository;

import com.vue_test.entity.DashboardStats;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DashboardStatsRepository extends JpaRepository<DashboardStats, Integer> {
    List<DashboardStats> findByCategoryIn(List<String> categories);
}
