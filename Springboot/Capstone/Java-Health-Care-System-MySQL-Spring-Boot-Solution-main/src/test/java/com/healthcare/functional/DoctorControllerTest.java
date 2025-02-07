package com.healthcare.functional;

import static com.healthcare.utils.MasterData.getDoctorDTO;
import static com.healthcare.utils.MasterData.getDoctorDTOList;
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

import com.healthcare.controller.DoctorController;
import com.healthcare.dto.DoctorDTO;
import com.healthcare.service.DoctorService;
import com.healthcare.utils.MasterData;

@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DoctorService doctorService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDoctor() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();

		when(doctorService.createDoctor(any())).thenReturn(doctorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/doctors")
				.content(MasterData.asJsonString(doctorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(doctorDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetDoctorById() throws Exception {
		DoctorDTO doctorDTO = getDoctorDTO();
		Long doctorId = doctorDTO.getId();

		when(doctorService.getDoctorById(doctorId)).thenReturn(doctorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctors/" + doctorId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(doctorDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetAllDoctors() throws Exception {
		List<DoctorDTO> doctorDTOList = getDoctorDTOList();

		when(doctorService.getAllDoctors()).thenReturn(doctorDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctors")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(doctorDTOList)) ? "true"
						: "false",
				businessTestFile);
	}
}
