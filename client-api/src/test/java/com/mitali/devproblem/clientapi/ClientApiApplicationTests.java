package com.mitali.devproblem.clientapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitali.devproblem.clientapi.client.Client;
import com.mitali.devproblem.clientapi.client.ClientController;
import com.mitali.devproblem.clientapi.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value= ClientController.class)
class ClientApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService clientService;

	@Test
	public void positiveTestCreateClient() throws Exception {
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("1234567891234");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/client/create";

		Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Uri)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void positiveTestUpdateClient() throws Exception {
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("1234567891234");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/client/update/1234567891234";

		Mockito.when(clientService.updateClient(Mockito.any(Client.class), Mockito.anyString())).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put(Uri)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
	}


	@Test
	public void testSearchByFirstName() throws  Exception{
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("1234567891235");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/clients/firstname/Aditi";

		Mockito.when(clientService.getClientByFirstName(Mockito.anyString())).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(Uri)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}


	@Test
	public void testSearchById() throws Exception {
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("1234567891235");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/clients/id/1234567891235";

		Mockito.when(clientService.getClientById(Mockito.anyString())).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(Uri)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	@Test
	public void testSearchByMobileNumber() throws Exception{
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("1234567891235");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/clients/mobilenumber/8345221155";

		Mockito.when(clientService.getClientByMobileNumber(Mockito.anyString())).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(Uri)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	@Test
	public void negativeTestCreateClientEmptyFirstName() throws Exception {
		Client mockClient = new Client();
		//mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("1234567891234");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/client/create";

		String expectedJson = "{\"firstName\":\"First Name can not be empty\"}";

		Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Uri)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void negativeTestCreateClientEmptyLastName() throws Exception {
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		//mockClient.setLastName("Singh");
		mockClient.setId("1234567891234");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/client/create";

		String expectedJson = "{\"lastName\":\"Last Name can not be empty\"}";

		Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Uri)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void negativeTestCreateClientEmptyId() throws Exception {
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		//mockClient.setId("1234567891234");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/client/create";

		String expectedJson = "{\"id\":\"Id can not be null\"}";

		Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Uri)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
	}

	@Test
	public void negativeTestCreateClientInvalidId() throws Exception {
		Client mockClient = new Client();
		mockClient.setFirstName("Aditi");
		mockClient.setLastName("Singh");
		mockClient.setId("12345678914");
		mockClient.setMobileNumber("8345221155");
		mockClient.setAddress("Kolkata");

		String inputJson = this.mapToJson(mockClient);
		String Uri = "/client/create";

		String expectedJson = "{\"id\":\"This is invalid South African id.Please enter correct format.\"}";

		Mockito.when(clientService.createClient(Mockito.any(Client.class))).thenReturn(mockClient);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Uri)
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

		String outputJson = mockHttpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
