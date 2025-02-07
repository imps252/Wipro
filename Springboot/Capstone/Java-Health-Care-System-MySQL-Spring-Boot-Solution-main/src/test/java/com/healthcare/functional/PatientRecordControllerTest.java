package com.healthcare.functional;

import static com.healthcare.utils.MasterData.getPatientRecordDTO;
import static com.healthcare.utils.MasterData.getPatientRecordDTOList;
import static com.healthcare.utils.TestUtils.businessTestFile;
import static com.healthcare.utils.TestUtils.currentTest;
import static com.healthcare.utils.TestUtils.testReport;
import static com.healthcare.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class PatientRecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PatientRecordService patientRecordService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreatePatientRecord() throws Exception {
		PatientRecordDTO patientRecordDTO = getPatientRecordDTO();

		when(patientRecordService.createPatientRecord(any())).thenReturn(patientRecordDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/patient-records")
				.content(MasterData.asJsonString(patientRecordDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetPatientRecordsByUser() throws Exception {
		List<PatientRecordDTO> patientRecordDTOList = getPatientRecordDTOList();
		Long userId = 1L;

		when(patientRecordService.getPatientRecordsByUser(userId)).thenReturn(patientRecordDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/patient-records/user/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testUpdatePatientRecord() throws Exception {
		PatientRecordDTO patientRecordDTO = getPatientRecordDTO();
		Long recordId = patientRecordDTO.getId();

		when(patientRecordService.updatePatientRecord(any(), any())).thenReturn(patientRecordDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/patient-records/" + recordId)
				.content(MasterData.asJsonString(patientRecordDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testDeletePatientRecord() throws Exception {
		Long recordId = 1L;
		when(patientRecordService.deletePatientRecord(recordId)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/patient-records/" + recordId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == 204 ? "true" : "false"), businessTestFile);
	}

	@Test
	public void testGetPatientRecordDetails() throws Exception {
		PatientRecordDTO patientRecordDTO = getPatientRecordDTO();
		Long recordId = patientRecordDTO.getId();

		when(patientRecordService.getPatientRecordDetails(recordId)).thenReturn(patientRecordDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/patient-records/" + recordId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetAllPatientRecords() throws Exception {
		List<PatientRecordDTO> patientRecordDTOList = getPatientRecordDTOList();

		when(patientRecordService.getAllPatientRecords()).thenReturn(patientRecordDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/patient-records")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testSearchPatientRecords() throws Exception {
		List<PatientRecordDTO> patientRecordDTOList = getPatientRecordDTOList();
		String query = "Hypertension";

		when(patientRecordService.searchPatientRecords(query)).thenReturn(patientRecordDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/patient-records/search?query=" + query)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetPatientRecordsByDoctor() throws Exception {
		List<PatientRecordDTO> patientRecordDTOList = getPatientRecordDTOList();
		Long doctorId = 1L;

		when(patientRecordService.getPatientRecordsByDoctor(doctorId)).thenReturn(patientRecordDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/patient-records/doctor/" + doctorId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testFlagPatientRecordForReview() throws Exception {
		PatientRecordDTO patientRecordDTO = getPatientRecordDTO();
		Long recordId = patientRecordDTO.getId();

		when(patientRecordService.flagPatientRecordForReview(any())).thenReturn(patientRecordDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/patient-records/flag/" + recordId)
				.content(MasterData.asJsonString(patientRecordDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(patientRecordDTO))
						? "true"
						: "false",
				businessTestFile);
	}
}
