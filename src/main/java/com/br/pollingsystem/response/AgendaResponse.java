package com.br.pollingsystem.response;

import com.br.pollingsystem.entity.SubjectEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class AgendaResponse {

    private List<SubjectEntity> agendaEnabled;
    private String message;
}
