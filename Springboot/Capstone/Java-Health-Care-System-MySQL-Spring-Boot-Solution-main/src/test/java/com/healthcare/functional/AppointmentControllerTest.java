package com.healthcare.functional;

import static com.healthcare.utils.MasterData.getAppointmentDTO;
import static com.healthcare.utils.MasterData.getAppointmentDTOList;
import static com.healthcare.utils.TestUtils.businessTestFile;
import static com.healthcare.utils.TestUtils.currentTest;
import static com.healthcare.utils.TestUtils.testReport;
import static com.healthcare.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.healthcare.controller.AppointmentController;
import com.healthcare.dto.AppointmentDTO;
import com.healthcare.service.AppointmentService;
import com.healthcare.utils.MasterData;

@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
public class AppointmentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppointmentService appointmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateAppointment() throws Exception {
		AppointmentDTO appointmentDTO = getAppointmentDTO();

		when(appointmentService.createAppointment(any())).thenReturn(appointmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/appointments")
				.content(MasterData.asJsonString(appointmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetUserAppointments() throws Exception {
		List<AppointmentDTO> appointmentDTOList = getAppointmentDTOList();
		Long userId = 1L;

		when(appointmentService.getUserAppointments(userId)).thenReturn(appointmentDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/user/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testUpdateAppointment() throws Exception {
		AppointmentDTO appointmentDTO = getAppointmentDTO();
		Long appointmentId = appointmentDTO.getId();

		when(appointmentService.updateAppointment(any(), any())).thenReturn(appointmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/appointments/" + appointmentId)
				.content(MasterData.asJsonString(appointmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testCancelAppointment() throws Exception {
		Long appointmentId = 1L;
		when(appointmentService.cancelAppointment(appointmentId)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/appointments/" + appointmentId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == 204 ? "true" : "false"), businessTestFile);
	}

	@Test
	public void testGetAppointmentDetails() throws Exception {
		AppointmentDTO appointmentDTO = getAppointmentDTO();
		Long appointmentId = appointmentDTO.getId();

		when(appointmentService.getAppointmentDetails(appointmentId)).thenReturn(appointmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/" + appointmentId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testRescheduleAppointment() throws Exception {
		AppointmentDTO appointmentDTO = getAppointmentDTO();
		Long appointmentId = appointmentDTO.getId();

		when(appointmentService.rescheduleAppointment(any(), any())).thenReturn(appointmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/appointments/reschedule/" + appointmentId)
				.content(MasterData.asJsonString(appointmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testCheckAppointmentStatus() throws Exception {
		Long appointmentId = 1L;
		String status = "Scheduled";

		when(appointmentService.checkAppointmentStatus(appointmentId)).thenReturn(status);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/status/" + appointmentId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getContentAsString().contentEquals(status) ? "true" : "false",
				businessTestFile);
	}

	@Test
	public void testGetAppointmentsByDate() throws Exception {
		List<AppointmentDTO> appointmentDTOList = getAppointmentDTOList();
		String date = "2024-07-16";

		when(appointmentService.getAppointmentsByDate(LocalDate.parse(date))).thenReturn(appointmentDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/date?date=" + date)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetAppointmentsByDoctor() throws Exception {
		List<AppointmentDTO> appointmentDTOList = getAppointmentDTOList();
		Long doctorId = 1L;

		when(appointmentService.getAppointmentsByDoctor(doctorId)).thenReturn(appointmentDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/doctor/" + doctorId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testMarkAppointmentAsCompleted() throws Exception {
		AppointmentDTO appointmentDTO = getAppointmentDTO();
		Long appointmentId = appointmentDTO.getId();

		when(appointmentService.markAppointmentAsCompleted(any())).thenReturn(appointmentDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/appointments/complete/" + appointmentId)
				.content(MasterData.asJsonString(appointmentDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTO))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetAppointmentHistoryForUser() throws Exception {
		List<AppointmentDTO> appointmentDTOList = getAppointmentDTOList();
		Long userId = 1L;

		when(appointmentService.getAppointmentHistoryForUser(userId)).thenReturn(appointmentDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/appointments/history/user/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(appointmentDTOList))
						? "true"
						: "false",
				businessTestFile);
	}
}
