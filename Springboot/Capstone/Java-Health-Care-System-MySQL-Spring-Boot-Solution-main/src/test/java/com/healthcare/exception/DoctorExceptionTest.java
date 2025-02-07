package com.healthcare.exception;

import static com.healthcare.utils.TestUtils.currentTest;
import static com.healthcare.utils.TestUtils.exceptionTestFile;
import static com.healthcare.utils.TestUtils.testReport;
import static com.healthcare.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.healthcare.controller.DoctorController;
import com.healthcare.dto.DoctorDTO;
import com.healthcare.service.DoctorService;
import com.healthcare.utils.MasterData;

@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc
public class DoctorExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService doctorService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetDoctorByIdNotFoundException() throws Exception {
		Long doctorId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Doctor not found");

		when(this.doctorService.getDoctorById(doctorId)).thenThrow(new NotFoundException("Doctor not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctors/" + doctorId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testAddDoctorInvalidDataException() throws Exception {
		DoctorDTO doctorDTO = new DoctorDTO(); // Create an invalid DoctorDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/doctors")
				.content(MasterData.asJsonString(doctorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}
}
