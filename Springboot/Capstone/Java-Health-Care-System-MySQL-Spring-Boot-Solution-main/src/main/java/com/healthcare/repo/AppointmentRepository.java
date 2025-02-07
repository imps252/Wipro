package com.healthcare.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND (:startDateTime IS NULL OR a.appointmentTime >= :startDateTime) AND (:endDateTime IS NULL OR a.appointmentTime <= :endDateTime)")
	List<Appointment> findAppointmentsByDoctorAndDateRange(@Param("doctorId") Long doctorId,
			@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

	@Query("SELECT a FROM Appointment a WHERE (:doctorId IS NULL OR a.doctor.id = :doctorId) AND a.appointmentTime BETWEEN :startDateTime AND :endDateTime")
	List<Appointment> findAppointmentsByDateRange(@Param("doctorId") Long doctorId,
			@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

	List<Appointment> findByUserId(Long userId);
}
