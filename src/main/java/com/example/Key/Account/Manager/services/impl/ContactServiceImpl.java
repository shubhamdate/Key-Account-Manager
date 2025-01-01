package com.example.Key.Account.Manager.services.impl;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateContactRequestDto;
import com.example.Key.Account.Manager.dto.UpdateContactRequestDto;
import com.example.Key.Account.Manager.entities.Contacts;
import com.example.Key.Account.Manager.entities.Leads;
import com.example.Key.Account.Manager.repository.ContactsRepository;
import com.example.Key.Account.Manager.repository.LeadsRepository;
import com.example.Key.Account.Manager.services.ContactService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactsRepository contactRepository;
    private final LeadsRepository leadsRepository;

    public ContactServiceImpl(ContactsRepository contactRepository, LeadsRepository leadsRepository) {
        this.contactRepository = contactRepository;
        this.leadsRepository = leadsRepository;
    }

    @Transactional
    public ApiResponse createContact(CreateContactRequestDto requestDto) {
        Optional<Leads> lead = leadsRepository.findById(requestDto.getLeadId());
        if (lead.isEmpty()) {
            return new ApiResponse("error", null, "Lead not found");
        }

        Contacts contact = new Contacts();
        contact.setName(requestDto.getName());
        contact.setRole(requestDto.getRole());
        contact.setPhone(requestDto.getPhone());
        contact.setEmail(requestDto.getEmail());
        contact.setLead(lead.get());
        contactRepository.save(contact);
        return new ApiResponse("success", null, "Contact created successfully");
    }

    @Transactional
    public ApiResponse getAllContactsByLeadId(Long leadId) {
        List<Contacts> contacts = contactRepository.findByLead_Id(leadId);
        return new ApiResponse("success", contacts, null);
    }

    @Transactional
    public ApiResponse updateContact(Long id, UpdateContactRequestDto requestDto) {
        Optional<Contacts> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            return new ApiResponse("error", null, "Contact not found");
        }

        contact.get().setName(requestDto.getName());
        contact.get().setRole(requestDto.getRole());
        contact.get().setPhone(requestDto.getPhone());
        contact.get().setEmail(requestDto.getEmail());

        contact.ifPresent(contactRepository::save);
        return new ApiResponse("success", null, "Contact updated successfully");
    }

    @Transactional
    public ApiResponse deleteContact(Long id) {
        Optional<Contacts> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            return new ApiResponse("error", null, "Contact not found");
        }

        contactRepository.delete(contact.get());
        return new ApiResponse("success", null, "Contact deleted successfully");
    }
}

