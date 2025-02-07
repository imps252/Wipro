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

import com.healthcare.controller.PatientRecordController;
import com.healthcare.dto.PatientRecordDTO;
import com.healthcare.service.PatientRecordService;
import com.healthcare.utils.MasterData;

@WebMvcTest(PatientRecordController.class)
@AutoConfigureMockMvc
public class PatientRecordExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientRecordService patientRecordService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetPatientRecordDetailsNotFoundException() throws Exception {
		Long recordId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Patient record not found");

		when(this.patientRecordService.getPatientRecordDetails(recordId))
				.thenThrow(new NotFoundException("Patient record not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/patient-records/" + recordId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testAddPatientRecordInvalidDataException() throws Exception {
		PatientRecordDTO patientRecordDTO = new PatientRecordDTO(); // Create an invalid PatientRecordDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/patient-records")
				.content(MasterData.asJsonString(patientRecordDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeletePatientRecordNotFoundException() throws Exception {
		Long recordId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Patient record not found");

		when(this.patientRecordService.deletePatientRecord(recordId))
				.thenThrow(new NotFoundException("Patient record not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/patient-records/" + recordId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
