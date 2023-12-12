package com.br.pollingsystem.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Messages {

    public static final String NOT_BE_NULL = "O campo não pode ser nulo";
    public static final String VOTE_SUCESS = "vote successfully counted ";
    public static final String ERROR_VOTE = "Error when computing your vote, check if the agenda is open or if you have already voted on it";
    public static final String CREATE_AGENDA_SUCESS = "agenda created successfully";
    public static final String NO_AGENDA_ENABLE = "We do not have active agendas";
    public static final String ALL_AGENDA_ENABLE = "all guidelines available";
    public static final String INVALID_CPF = "invalid CPF";
    public static final String NOT_VOTES_FOUND_AGENDA = "Not votes to count now, plis verify name of agenda or start the vote";



}
