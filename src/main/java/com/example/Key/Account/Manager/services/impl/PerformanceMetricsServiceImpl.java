package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.UpdatePerformanceMetricsDto;
import com.example.Key.Account.Manager.entities.PerformanceMetrics;
import com.example.Key.Account.Manager.repository.LeadsRepository;
import com.example.Key.Account.Manager.repository.PerformanceMetricsRepository;
import com.example.Key.Account.Manager.services.PerformanceMetricsService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PerformanceMetricsServiceImpl implements PerformanceMetricsService {

    private final PerformanceMetricsRepository metricsRepository;
    private final LeadsRepository leadsRepository;

    public PerformanceMetricsServiceImpl(PerformanceMetricsRepository metricsRepository, LeadsRepository leadsRepository) {
        this.metricsRepository = metricsRepository;
        this.leadsRepository = leadsRepository;
    }

    @Transactional
    public ApiResponse createMetricsForLead(Long leadId) {
        if (metricsRepository.findByLeadId(leadId).isPresent()) {
            return new ApiResponse("error", null, "Performance metrics already exist for this lead");
        }

        var lead = leadsRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.setLead(lead);
        metrics.setTotalOrders(0);
        metrics.setTotalRevenue(BigDecimal.valueOf(00.00));
        metrics.setAverageOrderValue(BigDecimal.valueOf(00.00));
        metrics.setLastOrderDate(null);
        metricsRepository.save(metrics);

        return new ApiResponse("success", null, "Performance metrics created successfully");
    }


    @Transactional
    public ApiResponse getTopPerformingLeads() {
        List<PerformanceMetrics> metrics = metricsRepository.findTopPerformingLeads();
        return new ApiResponse("success", metrics, null);
    }

    @Transactional
    public ApiResponse getMetricsByLeadId(Long leadId) {
        Optional<PerformanceMetrics> metrics = metricsRepository.findByLeadId(leadId);
        if(metrics.isEmpty()){
            return new ApiResponse("error", null, "Data not Found");
        }

        return new ApiResponse("success", metrics, null);
    }

    @Transactional
    public ApiResponse updateMetrics(Long leadId, UpdatePerformanceMetricsDto updateMetricsDto) {
        Optional<PerformanceMetrics> metrics = metricsRepository.findByLeadId(leadId);

        if(metrics.isEmpty()){
            return new ApiResponse("error", null, "Data not Found");
        }

        metrics.get().setTotalOrders(metrics.get().getTotalOrders() + updateMetricsDto.getTotalOrders());
        metrics.get().setTotalRevenue(metrics.get().getTotalRevenue().add(updateMetricsDto.getTotalRevenue()));
        metrics.get().setAverageOrderValue(metrics.get().getTotalRevenue().divide(BigDecimal.valueOf(metrics.get().getTotalOrders())));
        metrics.get().setLastOrderDate(LocalDate.parse(updateMetricsDto.getLastOrderDate()));
        metrics.ifPresent(metricsRepository::save);

        return new ApiResponse("success", null, "Metrics updated successfully");
    }
}
