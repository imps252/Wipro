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

import com.healthcare.dto.DoctorDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class DoctorBoundaryTest {

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
	public void testNameNotBlank() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setName("");
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameNotNull() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setName(null);
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSpecialtyNotBlank() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setSpecialty("");
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSpecialtyNotNull() throws IOException {
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setSpecialty(null);
		Set<ConstraintViolation<DoctorDTO>> violations = validator.validate(doctorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
