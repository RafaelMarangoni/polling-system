package com.br.pollingsystem.service;

import com.br.pollingsystem.DTO.VoteDTO;
import com.br.pollingsystem.Service.RegisterService;
import com.br.pollingsystem.exceptions.BusinessException;
import com.br.pollingsystem.projection.CountVoteProjection;
import com.br.pollingsystem.repository.RegisterVotesRepository;
import com.br.pollingsystem.response.CountVoteResponse;
import com.br.pollingsystem.utils.Messages;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceTest {

    @Mock
    private RegisterVotesRepository registerRepository;

    @InjectMocks
    private RegisterService registerService;

    public VoteDTO createVoteMockErrorCPF(){
        VoteDTO vote = new VoteDTO();
        vote.setVote(true);
        vote.setAssociateIdentification("12345678910");
        return vote;
    }

    public VoteDTO createVoteMockCPF(){
        VoteDTO vote = new VoteDTO();
        vote.setVote(true);
        vote.setAssociateIdentification("57001842060");
        vote.setTitle("Mock teste");
        vote.setNumberOfSubject(1);
        return vote;
    }

    @Test
    public void createVoteErrorCPF(){
        var returnError = registerService.createVote(createVoteMockErrorCPF());
        Assert.assertEquals("invalid CPF", returnError.getMessage());
    }

    @Test
    public void createVoteSucess(){
        when(registerRepository.insertResultIfEndSubjectIsAfterNow(anyBoolean(),anyLong(),anyString(),anyString())).thenReturn(1);
        var returnSucess = registerService.createVote(createVoteMockCPF());
        Assert.assertEquals("vote successfully counted ",returnSucess.getMessage());
        verify(registerRepository, times(1)).insertResultIfEndSubjectIsAfterNow(anyBoolean(),anyLong(),anyString(),anyString());

    }

    @Test
    public void createErrorValidateQuery(){
        when(registerRepository.insertResultIfEndSubjectIsAfterNow(anyBoolean(),anyLong(),anyString(),anyString())).thenReturn(0);
        var returnSucess = registerService.createVote(createVoteMockCPF());
        Assert.assertEquals("Error when computing your vote, check if the agenda is open or if you have already voted on it" ,returnSucess.getMessage());
        verify(registerRepository, times(1)).insertResultIfEndSubjectIsAfterNow(anyBoolean(),anyLong(),anyString(),anyString());
    }

    @Test
    public void getCountVotesValidTitleReturnsCountVoteResponse() throws BusinessException {
        String title = "Teste";
        CountVoteResponse projectionResponse = new CountVoteResponse("Teste", "10", "5", "15");

        when(registerRepository.getCountVotes(title)).thenReturn(convertToProjection(projectionResponse));

        CountVoteResponse result = registerService.getCountVotes(title);

        assertEquals("Teste", result.getTitle());
        assertEquals("10", result.getSim());
        assertEquals("5", result.getNao());
        assertEquals("15", result.getTotalGeral());

        verify(registerRepository, times(1)).getCountVotes(title);
    }

    @Test
    public void getCountVotesInvalidTitleThrowsBusinessException() {

        String invalidTitle = "InvalidTitle";
        when(registerRepository.getCountVotes(invalidTitle)).thenReturn(null);
        var responseError = registerService.getCountVotes(invalidTitle);
        assertEquals(Messages.NOT_VOTES_FOUND_AGENDA, responseError.getTitle());

        verify(registerRepository, times(1)).getCountVotes(invalidTitle);
    }

    private CountVoteProjection convertToProjection(CountVoteResponse response) {
        return new CountVoteProjection() {
            @Override
            public String getTitle() {
                return response.getTitle();
            }

            @Override
            public Long getTotalSim() {
                return Long.parseLong(response.getSim());
            }

            @Override
            public Long getTotalNao() {
                return Long.parseLong(response.getNao());
            }

            @Override
            public Long getTotalGeral() {
                return Long.parseLong(response.getTotalGeral());
            }
        };
    }
}
