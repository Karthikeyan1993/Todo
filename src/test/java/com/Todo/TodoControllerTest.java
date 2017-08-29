package com.Todo;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TodoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void verifyAllToDoList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todos").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}

	@Test
	public void verifyToDoById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.activte").exists()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Montly Status Report"))
				.andExpect(jsonPath("$.activte").value(true)).andDo(print());

	}

	@Test
	public void verifyInvalidToDoArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/k").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(400))
				.andExpect(jsonPath("$.message")
						.value("The request could not be understood by the server due to malformed syntax."))
				.andDo(print());
	}

	@Test
	public void verifyInvalidToDoId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/5").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(404))
				.andExpect(jsonPath("$.message").value("Todo does not exits")).andDo(print());
	}

	@Test
	public void verifyNullToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/0").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(404))
				.andExpect(jsonPath("$.message").value("Todo does not exits")).andDo(print());
	}

	@Test
	public void verifyToDoDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todos/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value(200))
				.andExpect(jsonPath("$.message").value("ToDo has been deleted")).andDo(print());
	}

	@Test
	public void verifyToDoInvalidDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/todos/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorCode").value(404))
				.andExpect(jsonPath("$.message").value("Todo id does not exit")).andDo(print());
	}

	@Test
	public void verifySaveToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/todos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"New ToDo Sample\", \"completed\" : \"false\",\"id\" : \"0\" }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.activte").exists())
				.andExpect(jsonPath("$.name").value("New ToDo Sample")).andExpect(jsonPath("$.activte").value(false))
				.andDo(print());
	}

	@Test
	public void verifySaveInvalidToDo() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/todos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\" : \"New ToDo Sample\", \"completed\" : \"false\",\"id\" : \"8\" }")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.errorCode").value(404))
				.andExpect(jsonPath("$.message").value("Id must not be defined")).andDo(print());
	}

}
