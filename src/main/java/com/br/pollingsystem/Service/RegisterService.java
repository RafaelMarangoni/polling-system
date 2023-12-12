package com.br.pollingsystem.Service;

import com.br.pollingsystem.projection.CountVoteProjection;
import com.br.pollingsystem.DTO.VoteDTO;
import com.br.pollingsystem.exceptions.InvalidCpfException;
import com.br.pollingsystem.repository.RegisterVotesRepository;
import com.br.pollingsystem.response.CountVoteResponse;
import com.br.pollingsystem.response.GeneralResponse;
import com.br.pollingsystem.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.br.pollingsystem.utils.Validate.validarCPF;

@Service
public class RegisterService {

    @Autowired
    private RegisterVotesRepository registerRepository;

    public GeneralResponse createVote(VoteDTO voteDTO) {
        try {
            validarCPF(voteDTO.getAssociateIdentification());
            var returnVoteConsult = registerRepository.insertResultIfEndSubjectIsAfterNow(
                    voteDTO.isVote(), voteDTO.getNumberOfSubject(), voteDTO.getAssociateIdentification(), voteDTO.getTitle());
            return getComputeVoteResponse(returnVoteConsult);
        } catch (InvalidCpfException ex) {
            return GeneralResponse.builder()
                    .message(Messages.INVALID_CPF)
                    .build();
        }
    }


    private GeneralResponse getComputeVoteResponse(int returnVoteConsult) {
        if (returnVoteConsult != 0){
            return GeneralResponse.builder()
                    .message(Messages.VOTE_SUCESS)
                    .build();
        }
        return GeneralResponse.builder()
                .message(Messages.ERROR_VOTE)
                .build();
    }

    public CountVoteResponse getCountVotes(String title){

            var projectionResponse = registerRepository.getCountVotes(title);

            if (Objects.nonNull(projectionResponse)) {
                return getCountVotes(projectionResponse);
            } else {
                return buildNotVotes();
            }

    }

    private static CountVoteResponse getCountVotes(CountVoteProjection projectionResponse) {
        return CountVoteResponse.builder()
                .title(projectionResponse.getTitle())
                .sim(projectionResponse.getTotalSim().toString())
                .nao(projectionResponse.getTotalNao().toString())
                .totalGeral(projectionResponse.getTotalGeral().toString())
                .build();
    }

    private static CountVoteResponse buildNotVotes() {
        return CountVoteResponse.builder()
                .title(Messages.NOT_VOTES_FOUND_AGENDA)
                .sim("0")
                .nao("0")
                .totalGeral("0")
                .build();
    }

}
