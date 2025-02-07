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

import com.healthcare.controller.UserController;
import com.healthcare.dto.UserDTO;
import com.healthcare.service.UserService;
import com.healthcare.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetUserDetailsNotFoundException() throws Exception {
		Long userId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found");

		when(this.userService.getUserDetails(userId)).thenThrow(new NotFoundException("User not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteUserNotFoundException() throws Exception {
		Long userId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found");

		when(this.userService.deleteUser(userId)).thenThrow(new NotFoundException("User not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/users/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

}
