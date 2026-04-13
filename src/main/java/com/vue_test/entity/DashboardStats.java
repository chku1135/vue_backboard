package com.vue_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "dashboard_stats")
public class DashboardStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String category;

    @Column(name = "sub_category")
    private String subCategory;

    @Column(name = "stat_value")
    private Long statValue;

    @Column(name = "stat_percent")
    private Double statPercent;

    @Column(name = "last_updated", insertable = false, updatable = false)
    private LocalDateTime lastUpdated;
}
