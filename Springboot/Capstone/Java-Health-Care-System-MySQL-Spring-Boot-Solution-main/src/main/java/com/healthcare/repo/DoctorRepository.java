package com.healthcare.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.healthcare.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT d FROM Doctor d WHERE d.specialty = ?1")
	List<Doctor> findBySpecialty(String specialty);

	List<Doctor> findByNameContainingIgnoreCase(String name);
}
