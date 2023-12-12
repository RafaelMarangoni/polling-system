package com.br.pollingsystem.Service;

import com.br.pollingsystem.DTO.SubjectDTO;
import com.br.pollingsystem.entity.SubjectEntity;
import com.br.pollingsystem.repository.SubjectRepository;
import com.br.pollingsystem.response.AgendaResponse;
import com.br.pollingsystem.response.GeneralResponse;
import com.br.pollingsystem.utils.Messages;
import com.br.pollingsystem.utils.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class AgendaService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private Validate validate;

    public AgendaResponse getAllPolling() {
        var response = subjectRepository.getAgendasEnabled();
        if(Objects.nonNull(response)){
            return  AgendaResponse.builder()
                    .agendaEnabled(response)
                    .message(Messages.ALL_AGENDA_ENABLE)
                    .build();
        }
        return AgendaResponse.builder()
                .message(Messages.NO_AGENDA_ENABLE)
                .build();
    }



    public GeneralResponse createSubject(SubjectDTO subjectDTO) {
        SubjectEntity createNewSubject = buildSubject(subjectDTO);
        subjectRepository.save(createNewSubject);
        return GeneralResponse.builder()
                .message(Messages.CREATE_AGENDA_SUCESS)
                .build();
    }

    private SubjectEntity buildSubject(SubjectDTO subjectDTO) {
        SubjectEntity createNewSubject = new SubjectEntity();
        createNewSubject.setStartSubject(Timestamp.valueOf(LocalDateTime.now()));
        createNewSubject.setTimeSubject(subjectDTO.getTimeDuration() == null ? 1 : subjectDTO.getTimeDuration());
        createNewSubject.setEndSubject(calculateEndVotes(subjectDTO.getTimeDuration(),Timestamp.valueOf(LocalDateTime.now())));
        createNewSubject.setTitleSubject(subjectDTO.getTitleSubject());
        return createNewSubject;
    }

    private Timestamp calculateEndVotes(Integer timeDuration, Timestamp timestamp) {
        Instant instant = timestamp.toInstant();
        Duration duration = Duration.ofMinutes(timeDuration);
        Instant novoInstant = instant.plus(duration);

        return Timestamp.from(novoInstant);
    }
}
