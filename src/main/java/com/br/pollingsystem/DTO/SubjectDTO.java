package com.br.pollingsystem.DTO;

import com.br.pollingsystem.utils.Messages;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SubjectDTO {

    @NotNull(message = Messages.NOT_BE_NULL)
    private String titleSubject;
    private Integer timeDuration;

}
