
package com.crud.msstudent.controllers;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.crud.msstudent.models.Student;
import com.crud.msstudent.services.StudentService;
import com.crud.msstudent.services.IStudent;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@WebMvcTest(controllers = { StudentController.class })
@ActiveProfiles({ "test" })
@Getter
public class StudentControllerTestBkp {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private StudentService studentService;

	private static final String STUDENT_URL = "/api/students";
	
	/*
	 * Some notes about the annotations used :
	 * 
	 * @RunWith(MockitoJUnitRunner.class) : Invokes the class MockitoJUnitRunner to
	 * run the tests instead of running in the standard built in class.
	 * 
	 * @Mock : Used to simulate the behavior of a real object, in this case, our
	 * repository
	 * 
	 * @InjectMocks : Creates an instance of the class and injects the mock created
	 * with the @Mock annotation into this instance
	 * 
	 * @Test : Tells JUnit that the method to which this annotation is attached can
	 * be run as a test case
	 */
	@Test
	public void fetchData() throws Exception {

		List<Student> data = Lists
				.list(Student.builder().id(1).firstname("abc").lastname("xyz").email("abc@gmail.com").build());
		when(studentService.getAllStudents()).thenReturn(data);

		MvcResult mvcResult = this.mockMvc.perform(get(STUDENT_URL)).andExpect(status().isOk()).andReturn();

		String contentAsString = mvcResult.getResponse().getContentAsString();
		assertThat(contentAsString, notNullValue());
		assertThat(contentAsString, is("[{\\\"id\\\":1,\\\"firstname\\\":\\\"abc\\\",\\\"lastname\\\":\\\"xyz\\\",\\\"email\\\":\\\"abc@gmail.com\\\"}]"));

	}

}
