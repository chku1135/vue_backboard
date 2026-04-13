package com.vue_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "dashboard_traffic")
public class DashboardTraffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    private Integer visits;
    private Integer pageviews;
    
    @Column(name = "unique_users")
    private Integer uniqueUsers;
}
