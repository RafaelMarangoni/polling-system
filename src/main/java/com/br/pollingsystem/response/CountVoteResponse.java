package com.br.pollingsystem.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountVoteResponse {

    private String title;
    private String sim;
    private String nao;
    private String totalGeral;
}
