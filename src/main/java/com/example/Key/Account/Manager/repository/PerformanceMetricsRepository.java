package com.example.Key.Account.Manager.repository;

import com.example.Key.Account.Manager.entities.PerformanceMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceMetricsRepository extends JpaRepository<PerformanceMetrics, Long> {
    Optional<PerformanceMetrics> findByLeadId(Long leadId);

    @Query("SELECT pm FROM PerformanceMetrics pm ORDER BY pm.totalRevenue DESC")
    List<PerformanceMetrics> findTopPerformingLeads();
}
