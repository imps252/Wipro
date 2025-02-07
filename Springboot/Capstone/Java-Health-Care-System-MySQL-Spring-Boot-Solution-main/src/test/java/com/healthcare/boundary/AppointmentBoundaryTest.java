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

import com.healthcare.dto.AppointmentDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class AppointmentBoundaryTest {

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
	public void testAppointmentUserIdNotNull() throws IOException {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setUserId(null);
		Set<ConstraintViolation<AppointmentDTO>> violations = validator.validate(appointmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAppointmentDoctorIdNotNull() throws IOException {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setDoctorId(null);
		Set<ConstraintViolation<AppointmentDTO>> violations = validator.validate(appointmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAppointmentTimeNotNull() throws IOException {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setAppointmentTime(null);
		Set<ConstraintViolation<AppointmentDTO>> violations = validator.validate(appointmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testStatusNotBlank() throws IOException {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setStatus("");
		Set<ConstraintViolation<AppointmentDTO>> violations = validator.validate(appointmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testStatusNotNull() throws IOException {
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setStatus(null);
		Set<ConstraintViolation<AppointmentDTO>> violations = validator.validate(appointmentDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
