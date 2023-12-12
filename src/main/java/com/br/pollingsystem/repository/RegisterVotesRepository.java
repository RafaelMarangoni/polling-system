package com.br.pollingsystem.repository;

import com.br.pollingsystem.entity.RegisterVotesEntity;
import com.br.pollingsystem.projection.CountVoteProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterVotesRepository extends JpaRepository<RegisterVotesEntity,Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO register_votes (vote, number_of_subject, associate_identification, title_subject) " +
            "SELECT :vote, :numberOfSubject, :associateIdentification, :title " +
            "FROM (SELECT 1) AS dummy " +
            "WHERE NOT EXISTS (" +
            "   SELECT 1 " +
            "   FROM register_votes r " +
            "   WHERE r.number_of_subject = :numberOfSubject " +
            "     AND r.associate_identification = :associateIdentification" +
            ") " +
            "AND EXISTS (" +
            "   SELECT 1 " +
            "   FROM subject s " +
            "   WHERE s.id = :numberOfSubject " +
            "     AND s.end_subject > NOW()" +
            ")", nativeQuery = true)
    int insertResultIfEndSubjectIsAfterNow(@Param("vote") Boolean vote,
                                           @Param("numberOfSubject") long numberOfSubject,
                                           @Param("associateIdentification") String associateIdentification,
                                           @Param("title") String title);


    @Query(value = "SELECT " +
            "    title_subject as title, " +
            "    SUM(CASE WHEN vote = TRUE THEN 1 ELSE 0 END) AS totalSim," +
            "    SUM(CASE WHEN vote = FALSE THEN 1 ELSE 0 END) AS totalNao," +
            "    COUNT(*) AS totalGeral" +
            " FROM " +
            "    register_votes" +
            " WHERE " +
            "    LOWER(title_subject) = LOWER(:title)" +
            " GROUP BY " +
            "    title_subject", nativeQuery = true)
    CountVoteProjection getCountVotes(String title);
}
