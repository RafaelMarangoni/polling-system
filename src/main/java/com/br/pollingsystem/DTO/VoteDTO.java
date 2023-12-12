package com.br.pollingsystem.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDTO {

    private int numberOfSubject;
    private String associateIdentification;
    private boolean vote;
    private String title;

}
