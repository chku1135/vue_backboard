package com.vue_test.repository;

import com.vue_test.entity.DashboardUserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardUserActivityRepository extends JpaRepository<DashboardUserActivity, Integer> {
}
