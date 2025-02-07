package com.healthcare.functional;

import static com.healthcare.utils.MasterData.getUserDTO;
import static com.healthcare.utils.MasterData.getUserDTOList;
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

import com.healthcare.controller.UserController;
import com.healthcare.dto.UserDTO;
import com.healthcare.service.UserService;
import com.healthcare.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterUser() throws Exception {
		UserDTO userDTO = getUserDTO();

		when(userService.registerUser(any())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users")
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testUpdateUserProfile() throws Exception {
		UserDTO userDTO = getUserDTO();
		Long userId = userDTO.getId();

		when(userService.updateUserProfile(any(), any())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/users/" + userId)
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetUserDetails() throws Exception {
		UserDTO userDTO = getUserDTO();
		Long userId = userDTO.getId();

		when(userService.getUserDetails(userId)).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testDeleteUser() throws Exception {
		Long userId = 1L;
		when(userService.deleteUser(userId)).thenReturn(true);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getStatus() == 204 ? "true" : "false"), businessTestFile);
	}

	@Test
	public void testSearchUsers() throws Exception {
		List<UserDTO> userDTOList = getUserDTOList();
		String query = "john";

		when(userService.searchUsers(query)).thenReturn(userDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/search?query=" + query)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTOList)) ? "true"
						: "false",
				businessTestFile);
	}
}
