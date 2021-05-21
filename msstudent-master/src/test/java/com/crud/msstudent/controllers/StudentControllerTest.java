package com.crud.msstudent.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.crud.msstudent.models.Student;
import com.crud.msstudent.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = StudentController.class)
@ActiveProfiles("test")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Student> studentList;
    
    private static final String STUDENT_URL = "/api/students";

	/*
	 * @BeforeEach void setUp() { this.studentList = Lists.newArrayList(
	 * Student.builder().firstname("rajib").lastname("samanta").email(
	 * "abc@gmail.com").build(),
	 * Student.builder().firstname("locahn").lastname("samanta").email(
	 * "xyz@gmail.com").build());
	 * 
	 * }
	 */
   

	@Test
	public void fetchData() throws Exception {

		List<Student> data = Lists
				.list(Student.builder().id(1).firstname("abc").lastname("xyz").email("abc@gmail.com").build());
		when(studentService.getAllStudents()).thenReturn(data);

		MvcResult mvcResult = this.mockMvc.perform(get(STUDENT_URL)).andExpect(status().isOk()).andReturn();

		String contentAsString = mvcResult.getResponse().getContentAsString();
		assertThat(contentAsString, notNullValue());
		assertThat(contentAsString, is("[{\"id\":1,\"firstname\":\"abc\",\"lastname\":\"xyz\",\"email\":\"abc@gmail.com\"}]"));

	}
    

}
