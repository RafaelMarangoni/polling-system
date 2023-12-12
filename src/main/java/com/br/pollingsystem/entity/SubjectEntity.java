package com.br.pollingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "subject", schema = "public")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_subject")
    private Timestamp startSubject;
    @Column(name = "time_subject")
    private Integer timeSubject;
    @Column(name = "end_subject")
    private Timestamp endSubject;
    @Column(name = "title_subject")
    private String titleSubject;

}

