package com.vue_test.repository;

import com.vue_test.entity.DashboardTraffic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DashboardTrafficRepository extends JpaRepository<DashboardTraffic, Integer> {
    List<DashboardTraffic> findTop7ByOrderByRecordDateDesc();
}
