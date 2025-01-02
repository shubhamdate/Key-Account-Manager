package com.example.Key.Account.Manager.controllers;

import com.example.Key.Account.Manager.dto.ApiResponse;
import com.example.Key.Account.Manager.dto.CreateContactRequestDto;
import com.example.Key.Account.Manager.dto.UpdateContactRequestDto;
import com.example.Key.Account.Manager.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createContact(@RequestBody CreateContactRequestDto request) {
        ApiResponse response = contactService.createContact(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllContacts(@PathVariable Long leadId) {
        ApiResponse response = contactService.getAllContactsByLeadId(leadId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> updateContact(@PathVariable Long id, @RequestBody UpdateContactRequestDto request) {
        ApiResponse response = contactService.updateContact(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteContact(@PathVariable Long id) {
        ApiResponse response = contactService.deleteContact(id);
        return ResponseEntity.ok(response);
    }
}
