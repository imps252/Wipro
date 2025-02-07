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

import com.healthcare.controller.AppointmentController;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.service.AppointmentService;
import com.healthcare.utils.MasterData;

@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
public class AppointmentExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppointmentService appointmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAppointmentDetailsNotFoundException() throws Exception {
		Long appointmentId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Appointment not found");

		when(this.appointmentService.getAppointmentDetails(appointmentId))
				.thenThrow(new NotFoundException("Appointment not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/" + appointmentId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testAddAppointmentInvalidDataException() throws Exception {
		AppointmentDTO appointmentDTO = new AppointmentDTO(); // Create an invalid AppointmentDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/appointments")
				.content(MasterData.asJsonString(appointmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testCancelAppointmentNotFoundException() throws Exception {
		Long appointmentId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Appointment not found");

		when(this.appointmentService.cancelAppointment(appointmentId))
				.thenThrow(new NotFoundException("Appointment not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/appointments/" + appointmentId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
