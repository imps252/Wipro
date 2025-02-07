package com.healthcare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.entity.PatientRecord;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {

	@Query("SELECT pr FROM PatientRecord pr WHERE pr.user.id = ?1 AND pr.flaggedForReview = true")
	List<PatientRecord> findFlaggedRecordsByUser(Long userId);

	List<PatientRecord> findByDiagnosisContainingIgnoreCase(String diagnosis);
}
