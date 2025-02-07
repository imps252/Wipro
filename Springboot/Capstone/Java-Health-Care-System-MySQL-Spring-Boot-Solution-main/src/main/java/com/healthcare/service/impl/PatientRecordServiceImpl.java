package com.healthcare.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.dto.PatientRecordDTO;
import com.healthcare.entity.Doctor;
import com.healthcare.entity.PatientRecord;
import com.healthcare.entity.User;
import com.healthcare.exception.NotFoundException;
import com.healthcare.repo.PatientRecordRepository;
import com.healthcare.service.PatientRecordService;

@Service
public class PatientRecordServiceImpl implements PatientRecordService {

	@Autowired
	private PatientRecordRepository patientRecordRepository;

	@Override
	public PatientRecordDTO createPatientRecord(PatientRecordDTO patientRecordDTO) {
		PatientRecord patientRecord = convertToEntity(patientRecordDTO);
		patientRecord = patientRecordRepository.save(patientRecord);
		return convertToDTO(patientRecord);
	}

	@Override
	public List<PatientRecordDTO> getPatientRecordsByUser(Long userId) {
		List<PatientRecord> patientRecords = patientRecordRepository.findFlaggedRecordsByUser(userId);
		return patientRecords.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public PatientRecordDTO updatePatientRecord(Long recordId, PatientRecordDTO patientRecordDTO) {
		Optional<PatientRecord> optionalPatientRecord = patientRecordRepository.findById(recordId);
		if (optionalPatientRecord.isPresent()) {
			PatientRecord patientRecord = optionalPatientRecord.get();
			patientRecord.setDate(patientRecordDTO.getDate());
			patientRecord.setDiagnosis(patientRecordDTO.getDiagnosis());
			patientRecord.setTreatment(patientRecordDTO.getTreatment());
			patientRecord.setFlaggedForReview(patientRecordDTO.isFlaggedForReview());
			patientRecord = patientRecordRepository.save(patientRecord);
			return convertToDTO(patientRecord);
		} else {
			throw new NotFoundException("Patient record not found with id " + recordId);
		}
	}

	@Override
	public Boolean deletePatientRecord(Long recordId) {
		Optional<PatientRecord> optionalPatientRecord = patientRecordRepository.findById(recordId);
		if (optionalPatientRecord.isPresent()) {
			patientRecordRepository.deleteById(recordId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public PatientRecordDTO getPatientRecordDetails(Long recordId) {
		Optional<PatientRecord> optionalPatientRecord = patientRecordRepository.findById(recordId);
		if (optionalPatientRecord.isPresent()) {
			return convertToDTO(optionalPatientRecord.get());
		} else {
			throw new NotFoundException("Patient record not found with id " + recordId);
		}
	}

	@Override
	public List<PatientRecordDTO> getAllPatientRecords() {
		List<PatientRecord> patientRecords = patientRecordRepository.findAll();
		return patientRecords.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<PatientRecordDTO> searchPatientRecords(String query) {
		List<PatientRecord> patientRecords = patientRecordRepository.findByDiagnosisContainingIgnoreCase(query);
		return patientRecords.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<PatientRecordDTO> getPatientRecordsByDoctor(Long doctorId) {
		List<PatientRecord> patientRecords = patientRecordRepository.findAll().stream()
				.filter(record -> record.getDoctor().getId().equals(doctorId)).collect(Collectors.toList());
		return patientRecords.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public PatientRecordDTO flagPatientRecordForReview(Long recordId) {
		Optional<PatientRecord> optionalPatientRecord = patientRecordRepository.findById(recordId);
		if (optionalPatientRecord.isPresent()) {
			PatientRecord patientRecord = optionalPatientRecord.get();
			patientRecord.setFlaggedForReview(true);
			patientRecord = patientRecordRepository.save(patientRecord);
			return convertToDTO(patientRecord);
		} else {
			throw new NotFoundException("Patient record not found with id " + recordId);
		}
	}

	private PatientRecordDTO convertToDTO(PatientRecord patientRecord) {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setId(patientRecord.getId());
		patientRecordDTO.setUserId(patientRecord.getUser().getId());
		patientRecordDTO.setDoctorId(patientRecord.getDoctor().getId());
		patientRecordDTO.setDate(patientRecord.getDate());
		patientRecordDTO.setDiagnosis(patientRecord.getDiagnosis());
		patientRecordDTO.setTreatment(patientRecord.getTreatment());
		patientRecordDTO.setFlaggedForReview(patientRecord.isFlaggedForReview());
		return patientRecordDTO;
	}

	private PatientRecord convertToEntity(PatientRecordDTO patientRecordDTO) {
		PatientRecord patientRecord = new PatientRecord();
		patientRecord.setId(patientRecordDTO.getId());
		User user = new User();
		user.setId(patientRecordDTO.getUserId());
		patientRecord.setUser(user);
		Doctor doctor = new Doctor();
		doctor.setId(patientRecordDTO.getDoctorId());
		patientRecord.setDoctor(doctor);
		patientRecord.setDate(patientRecordDTO.getDate());
		patientRecord.setDiagnosis(patientRecordDTO.getDiagnosis());
		patientRecord.setTreatment(patientRecordDTO.getTreatment());
		patientRecord.setFlaggedForReview(patientRecordDTO.isFlaggedForReview());
		return patientRecord;
	}
}
