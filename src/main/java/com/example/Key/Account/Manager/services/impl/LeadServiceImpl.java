package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateLeadRequestDto;
import com.example.Key.Account.Manager.dto.UpdateLeadRequestDto;
import com.example.Key.Account.Manager.entities.Leads;
import com.example.Key.Account.Manager.entities.PerformanceMetrics;
import com.example.Key.Account.Manager.entities.Users;
import com.example.Key.Account.Manager.repository.LeadsRepository;
import com.example.Key.Account.Manager.repository.PerformanceMetricsRepository;
import com.example.Key.Account.Manager.repository.UsersRepository;
import com.example.Key.Account.Manager.services.LeadService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class LeadServiceImpl implements LeadService {

    private final UsersRepository userRepository;
    private final LeadsRepository leadRepository;
    private final PerformanceMetricsRepository metricsRepository;

    public LeadServiceImpl(UsersRepository userRepository, LeadsRepository leadRepository, PerformanceMetricsRepository metricsRepository) {
        this.userRepository = userRepository;
        this.leadRepository = leadRepository;
        this.metricsRepository = metricsRepository;
    }


    @Transactional
    public ApiResponse createLead(CreateLeadRequestDto requestDto) {

        Optional<Users> users = userRepository.findById(requestDto.getAssignedTo());
        if(users.isEmpty()){
            return new ApiResponse("error", null, "user not found");
        }

        Leads lead = new Leads();
        lead.setName(requestDto.getName());
        lead.setAddress(requestDto.getAddress());
        lead.setStatus(requestDto.getStatus());
        lead.setUser(users.get());
        leadRepository.save(lead);

        PerformanceMetrics metrics = new PerformanceMetrics();
        metrics.setLead(lead);
        metrics.setTotalOrders(0);
        metrics.setTotalRevenue(BigDecimal.valueOf(00.00));
        metrics.setAverageOrderValue(BigDecimal.valueOf(00.00));
        metrics.setLastOrderDate(null);
        metricsRepository.save(metrics);

        return new ApiResponse("success", null, "Lead Created Successfully");
    }

    @Transactional
    public ApiResponse getAllLeads(String status, Long assignedTo) {

        List<Leads> leads;
        if (status != null && assignedTo != null) {
            leads = leadRepository.findByStatusIgnoreCaseAndUser_Id(status, assignedTo);
        } else if (status != null) {
            leads = leadRepository.findByStatusIgnoreCase(status);
        } else if (assignedTo != null) {
            leads = leadRepository.findByUser_Id(assignedTo);
        } else {
            leads = leadRepository.findAll();
        }
        return new ApiResponse("success", leads, null);
    }

    @Transactional
    public ApiResponse getLeadById(Long id) {

        Optional<Leads> lead = leadRepository.findById(id);

        if(lead.isEmpty()){
            return new ApiResponse("error", lead, "Data not Found");
        }

        return new ApiResponse("success", lead, null);
    }

    @Transactional
    public ApiResponse updateLead(Long id, UpdateLeadRequestDto request) {

        Optional<Leads> lead = leadRepository.findById(id);
        if(lead.isEmpty()){
            return new ApiResponse("error", null, "Lead not found");
        }

        Optional<Users> users = userRepository.findById(request.getAssignedTo());
        if(users.isEmpty()){
            return new ApiResponse("error", null, "user not found");
        }

        lead.get().setName(request.getName());
        lead.get().setAddress(request.getAddress());
        lead.get().setStatus(request.getStatus());
        lead.get().setUser(users.get());
        lead.ifPresent(leadRepository::save);
        return new ApiResponse("success", null, "Data Updated Successfully");
    }
}
