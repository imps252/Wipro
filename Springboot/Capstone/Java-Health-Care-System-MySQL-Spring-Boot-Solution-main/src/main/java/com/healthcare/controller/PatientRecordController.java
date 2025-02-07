package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.dto.PatientRecordDTO;
import com.healthcare.service.PatientRecordService;

@RestController
@RequestMapping("/api/patient-records")
public class PatientRecordController {

	@Autowired
	private PatientRecordService patientRecordService;

	@PostMapping
	public ResponseEntity<PatientRecordDTO> createPatientRecord(
			@Validated @RequestBody PatientRecordDTO patientRecordDTO) {
		PatientRecordDTO createdPatientRecord = patientRecordService.createPatientRecord(patientRecordDTO);
		return ResponseEntity.ok(createdPatientRecord);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PatientRecordDTO>> getPatientRecordsByUser(@PathVariable Long userId) {
		List<PatientRecordDTO> patientRecords = patientRecordService.getPatientRecordsByUser(userId);
		return ResponseEntity.ok(patientRecords);
	}

	@PutMapping("/{recordId}")
	public ResponseEntity<PatientRecordDTO> updatePatientRecord(@PathVariable Long recordId,
			@Validated @RequestBody PatientRecordDTO patientRecordDTO) {
		PatientRecordDTO updatedPatientRecord = patientRecordService.updatePatientRecord(recordId, patientRecordDTO);
		return ResponseEntity.ok(updatedPatientRecord);
	}

	@DeleteMapping("/{recordId}")
	public ResponseEntity<Void> deletePatientRecord(@PathVariable Long recordId) {
		Boolean isDeleted = patientRecordService.deletePatientRecord(recordId);
		if (isDeleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{recordId}")
	public ResponseEntity<PatientRecordDTO> getPatientRecordDetails(@PathVariable Long recordId) {
		PatientRecordDTO patientRecordDTO = patientRecordService.getPatientRecordDetails(recordId);
		return ResponseEntity.ok(patientRecordDTO);
	}

	@GetMapping
	public ResponseEntity<List<PatientRecordDTO>> getAllPatientRecords() {
		List<PatientRecordDTO> patientRecords = patientRecordService.getAllPatientRecords();
		return ResponseEntity.ok(patientRecords);
	}

	@GetMapping("/search")
	public ResponseEntity<List<PatientRecordDTO>> searchPatientRecords(@RequestParam String query) {
		List<PatientRecordDTO> patientRecords = patientRecordService.searchPatientRecords(query);
		return ResponseEntity.ok(patientRecords);
	}

	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<List<PatientRecordDTO>> getPatientRecordsByDoctor(@PathVariable Long doctorId) {
		List<PatientRecordDTO> patientRecords = patientRecordService.getPatientRecordsByDoctor(doctorId);
		return ResponseEntity.ok(patientRecords);
	}

	@PutMapping("/flag/{recordId}")
	public ResponseEntity<PatientRecordDTO> flagPatientRecordForReview(@PathVariable Long recordId) {
		PatientRecordDTO flaggedPatientRecord = patientRecordService.flagPatientRecordForReview(recordId);
		return ResponseEntity.ok(flaggedPatientRecord);
	}
}
