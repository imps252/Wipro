package com.healthcare.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.dto.DoctorDTO;
import com.healthcare.dto.PatientRecordDTO;
import com.healthcare.dto.UserDTO;

public class MasterData {

	public static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("john_doe");
		userDTO.setEmail("john.doe@example.com");
		userDTO.setPassword("password123");
		userDTO.setFirstName("John");
		userDTO.setLastName("Doe");
		userDTO.setActive(true);
		return userDTO;
	}

	public static List<UserDTO> getUserDTOList() {
		List<UserDTO> userDTOList = new ArrayList<>();
		userDTOList.add(getUserDTO());
		return userDTOList;
	}

	public static DoctorDTO getDoctorDTO() {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setId(1L);
		doctorDTO.setName("Dr. Smith");
		doctorDTO.setSpecialty("Cardiology");
		return doctorDTO;
	}

	public static List<DoctorDTO> getDoctorDTOList() {
		List<DoctorDTO> doctorDTOList = new ArrayList<>();
		doctorDTOList.add(getDoctorDTO());
		return doctorDTOList;
	}

	public static PatientRecordDTO getPatientRecordDTO() {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setId(1L);
		patientRecordDTO.setUserId(1L);
		patientRecordDTO.setDoctorId(1L);
		patientRecordDTO.setDate(LocalDate.now());
		patientRecordDTO.setDiagnosis("Hypertension");
		patientRecordDTO.setTreatment("Medication and lifestyle changes");
		patientRecordDTO.setFlaggedForReview(false);
		return patientRecordDTO;
	}

	public static List<PatientRecordDTO> getPatientRecordDTOList() {
		List<PatientRecordDTO> patientRecordDTOList = new ArrayList<>();
		patientRecordDTOList.add(getPatientRecordDTO());
		return patientRecordDTOList;
	}

	public static AppointmentDTO getAppointmentDTO() {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(1L);
		appointmentDTO.setUserId(1L);
		appointmentDTO.setDoctorId(1L);
		appointmentDTO.setAppointmentTime(LocalDateTime.now());
		appointmentDTO.setStatus("Scheduled");
		return appointmentDTO;
	}

	public static List<AppointmentDTO> getAppointmentDTOList() {
		List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
		appointmentDTOList.add(getAppointmentDTO());
		return appointmentDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
