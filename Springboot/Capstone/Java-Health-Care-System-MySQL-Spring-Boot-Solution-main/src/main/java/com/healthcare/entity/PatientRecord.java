package com.healthcare.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_records")
public class PatientRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private String diagnosis;

	@Column(nullable = false)
	private String treatment;

	@Column(nullable = false)
	private boolean flaggedForReview;

	public PatientRecord() {
		super();
	}

	public PatientRecord(Long id, User user, Doctor doctor, LocalDate date, String diagnosis, String treatment,
			boolean flaggedForReview) {
		super();
		this.id = id;
		this.user = user;
		this.doctor = doctor;
		this.date = date;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
		this.flaggedForReview = flaggedForReview;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public boolean isFlaggedForReview() {
		return flaggedForReview;
	}

	public void setFlaggedForReview(boolean flaggedForReview) {
		this.flaggedForReview = flaggedForReview;
	}
}
