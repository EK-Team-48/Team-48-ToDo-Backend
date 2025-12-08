package com.example.kromannreumert.casee.controller;

import com.example.kromannreumert.casee.dto.CaseDeleteRequestDTO;
import com.example.kromannreumert.casee.dto.CaseRequestDTO;
import com.example.kromannreumert.casee.dto.CaseResponseDTO;
import com.example.kromannreumert.casee.dto.CaseUpdateRequest;
import com.example.kromannreumert.casee.service.CaseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cases")
@CrossOrigin("*")
public class CaseController {

    @Autowired
    CaseeService caseeService;

    @PostMapping("")
    public ResponseEntity<?> createCase(@RequestBody CaseRequestDTO request, Principal principal) {
        CaseResponseDTO response = caseeService.createCase(request, principal);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("")
    public ResponseEntity<?> getCases(Principal principal) {
        return new ResponseEntity<>(caseeService.getAllCases(principal), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateCase(@RequestBody CaseUpdateRequest request, Principal principal) {
        return new ResponseEntity<>(caseeService.updateCase(request, principal), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteCase(@RequestBody CaseDeleteRequestDTO request, Principal principal) {
        return ResponseEntity.ok(caseeService.deleteCase(request, principal));
    }


    }



