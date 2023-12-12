package com.br.pollingsystem.controller;

import com.br.pollingsystem.DTO.VoteDTO;
import com.br.pollingsystem.Service.RegisterService;
import com.br.pollingsystem.response.CountVoteResponse;
import com.br.pollingsystem.response.GeneralResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Register Controller", description = "responsable to create, control and some all votes")
public class RegisterVotesController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/vote")
    private ResponseEntity<GeneralResponse> vote(@RequestBody VoteDTO voteDTO){
        return ResponseEntity.ok().body(registerService.createVote(voteDTO));
    }

    @GetMapping("/count-votes")
    private ResponseEntity<CountVoteResponse> countVotes(@RequestParam String title) {
        return ResponseEntity.ok().body(registerService.getCountVotes(title));

    }
}
