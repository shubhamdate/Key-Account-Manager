package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateCallPlanRequestDto;
import com.example.Key.Account.Manager.entities.CallSchedule;
import com.example.Key.Account.Manager.repository.CallScheduleRepository;
import com.example.Key.Account.Manager.repository.LeadsRepository;
import com.example.Key.Account.Manager.services.CallPlanningService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CallPlanningServiceImpl implements CallPlanningService {

    private final CallScheduleRepository callScheduleRepository;
    private final LeadsRepository leadsRepository;

    public CallPlanningServiceImpl(CallScheduleRepository callScheduleRepository, LeadsRepository leadsRepository) {
        this.callScheduleRepository = callScheduleRepository;
        this.leadsRepository = leadsRepository;
    }

    @Transactional
    public ApiResponse createCallPlan(CreateCallPlanRequestDto requestDto) {
        Optional<CallSchedule> existingSchedule = callScheduleRepository.findByLead_Id(requestDto.getLeadId());
        if (existingSchedule.isPresent()) {
            return new ApiResponse("error", null, "Call plan already exists for this lead");
        }

        var lead = leadsRepository.findById(requestDto.getLeadId())
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        LocalDate nextCallDate;
        try {
            nextCallDate = requestDto.getNextCallDate();
        } catch (Exception e) {
            return new ApiResponse("error", null, "Invalid date format for nextCallDate. Use YYYY-MM-DD.");
        }

        CallSchedule schedule = new CallSchedule();
        schedule.setLead(lead);
        schedule.setCallFrequency(requestDto.getCallFrequency());
        schedule.setNextCallDate(nextCallDate);

        callScheduleRepository.save(schedule);

        return new ApiResponse("success", null, "Call plan created successfully");
    }

    @Override
    public ApiResponse getTodayCallPlans() {
        LocalDate today = LocalDate.now();
        List<CallSchedule> schedules = callScheduleRepository.findByNextCallDate(today);
        return new ApiResponse("success", schedules, null);
    }

    @Transactional
    @Override
    public ApiResponse updateCallFrequency(Long leadId, int frequencyInDays) {
        Optional<CallSchedule> scheduleOpt = callScheduleRepository.findByLead_Id(leadId);
        if (scheduleOpt.isEmpty()) {
            return new ApiResponse("error", null, "Call schedule not found for this lead");
        }

        CallSchedule schedule = scheduleOpt.get();
        schedule.setCallFrequency(frequencyInDays);
        schedule.setNextCallDate(schedule.getLastCallDate().plusDays(frequencyInDays));

        callScheduleRepository.save(schedule);
        return new ApiResponse("success", null, "Call frequency updated successfully");
    }

    @Transactional
    @Override
    public ApiResponse updateLastCallDetails(Long leadId, String lastCallDate) {
        Optional<CallSchedule> scheduleOpt = callScheduleRepository.findByLead_Id(leadId);
        if (scheduleOpt.isEmpty()) {
            return new ApiResponse("error", null, "Call schedule not found for this lead");
        }

        CallSchedule schedule = scheduleOpt.get();
        LocalDate parsedLastCallDate = LocalDate.parse(lastCallDate);
        schedule.setLastCallDate(parsedLastCallDate);
        schedule.setNextCallDate(parsedLastCallDate.plusDays(schedule.getCallFrequency()));

        callScheduleRepository.save(schedule);
        return new ApiResponse("success", null, "Last call details updated successfully");
    }
}
