package com.br.pollingsystem.controller;

import com.br.pollingsystem.DTO.SubjectDTO;
import com.br.pollingsystem.Service.AgendaService;
import com.br.pollingsystem.response.AgendaResponse;
import com.br.pollingsystem.response.GeneralResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Polling Controller", description = "Get enabled agendas and create new agenda")
public class PollingController {

    @Autowired
    AgendaService agendaService;

    @GetMapping("/get-agenda-enabled")
    private ResponseEntity<AgendaResponse>getAll(){
        var response = agendaService.getAllPolling();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create-subject")
    private ResponseEntity<GeneralResponse>createSubject(@RequestBody SubjectDTO subjectDTO){
        return ResponseEntity.ok().body(agendaService.createSubject(subjectDTO));

    }
}
