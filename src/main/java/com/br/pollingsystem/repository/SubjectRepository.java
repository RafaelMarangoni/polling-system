package com.br.pollingsystem.repository;

import com.br.pollingsystem.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity,Long> {
    @Query(value = "SELECT * FROM subject s where s.end_subject > NOW()", nativeQuery = true)
    List<SubjectEntity> getAgendasEnabled();
}
