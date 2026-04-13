package com.vue_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = "dashboard_user_activity")
public class DashboardUserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "usage_percent")
    private Integer usagePercent;

    private String period;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "last_activity")
    private String lastActivity;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "registered_at")
    private LocalDate registeredAt;
}
