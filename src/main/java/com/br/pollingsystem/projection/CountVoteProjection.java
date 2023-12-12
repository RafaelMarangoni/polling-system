package com.br.pollingsystem.projection;

public interface CountVoteProjection {
    String getTitle();
    Long getTotalSim();
    Long getTotalNao();
    Long getTotalGeral();
}
