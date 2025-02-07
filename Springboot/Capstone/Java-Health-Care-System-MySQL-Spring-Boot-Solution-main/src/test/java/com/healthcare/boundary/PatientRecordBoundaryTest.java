package com.healthcare.boundary;

import static com.healthcare.utils.TestUtils.boundaryTestFile;
import static com.healthcare.utils.TestUtils.currentTest;
import static com.healthcare.utils.TestUtils.testReport;
import static com.healthcare.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.healthcare.dto.PatientRecordDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PatientRecordBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testPatientUserIdNotNull() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setUserId(null);
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPatientDoctorIdNotNull() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setDoctorId(null);
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDateNotNull() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setDate(null);
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDiagnosisNotBlank() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setDiagnosis("");
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDiagnosisNotNull() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setDiagnosis(null);
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTreatmentNotBlank() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setTreatment("");
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTreatmentNotNull() throws IOException {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO();
		patientRecordDTO.setTreatment(null);
		Set<ConstraintViolation<PatientRecordDTO>> violations = validator.validate(patientRecordDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
