package com.br.pollingsystem.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "register_votes", schema = "public")
@Getter
@Setter
@Builder
public class RegisterVotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vote")
    private Boolean vote;

    @ManyToOne
    @JoinColumn(name = "number_of_subject", referencedColumnName = "id")
    private SubjectEntity subject;

    @Column(name = "associate_identification")
    private String associate;

    @Column(name = "title_subject")
    private String titleSubject;

}
