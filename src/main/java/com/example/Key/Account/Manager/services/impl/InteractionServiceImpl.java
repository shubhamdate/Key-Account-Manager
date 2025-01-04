package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateInteractionRequestDto;
import com.example.Key.Account.Manager.dto.UpdateInteractionRequestDto;
import com.example.Key.Account.Manager.entities.Interactions;
import com.example.Key.Account.Manager.entities.Leads;
import com.example.Key.Account.Manager.repository.InteractionsRepository;
import com.example.Key.Account.Manager.repository.LeadsRepository;
import com.example.Key.Account.Manager.services.InteractionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionServiceImpl implements InteractionService {

    private final InteractionsRepository interactionRepository;
    private final LeadsRepository leadsRepository;

    public InteractionServiceImpl(InteractionsRepository interactionRepository, LeadsRepository leadsRepository) {
        this.interactionRepository = interactionRepository;
        this.leadsRepository = leadsRepository;
    }

    @Transactional
    public ApiResponse createInteraction(CreateInteractionRequestDto requestDto) {
        Optional<Leads> lead = leadsRepository.findById(requestDto.getLeadId());
        if (lead.isEmpty()) {
            return new ApiResponse("error", null, "Lead not found");
        }

        Interactions interaction = new Interactions();
        interaction.setLead(lead.get());
        interaction.setInteractionDate(requestDto.getInteractionDate());
        interaction.setInteractionType(requestDto.getInteractionType());
        interaction.setDetails(requestDto.getDetails());

        interactionRepository.save(interaction);
        return new ApiResponse("success", null, "Interaction created successfully");
    }

    @Transactional
    public ApiResponse getAllInteractionsByLeadId(Long leadId) {
        List<Interactions> interactions = interactionRepository.findByLead_Id(leadId);
        if(interactions.isEmpty()) {
            return new ApiResponse("error", null, "Interaction not found");
        }
        return new ApiResponse("success", interactions, null);
    }

    @Transactional
    public ApiResponse updateInteraction(Long id, UpdateInteractionRequestDto requestDto) {
        Optional<Interactions> interaction = interactionRepository.findById(id);
        if (interaction.isEmpty()) {
            return new ApiResponse("error", null, "Interaction not found");
        }

        interaction.get().setInteractionDate(requestDto.getInteractionDate());
        interaction.get().setInteractionType(requestDto.getInteractionType());
        interaction.get().setDetails(requestDto.getDetails());
        interaction.ifPresent(interactionRepository::save);
        return new ApiResponse("success", null, "Interaction updated successfully");
    }

    @Transactional
    public ApiResponse deleteInteraction(Long id) {
        Optional<Interactions> interaction = interactionRepository.findById(id);
        if (interaction.isEmpty()) {
            return new ApiResponse("error", null, "Interaction not found");
        }

        interactionRepository.delete(interaction.get());
        return new ApiResponse("success", null, "Interaction deleted successfully");
    }
}

