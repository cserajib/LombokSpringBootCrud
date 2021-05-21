package com.crud.msstudent.controllers;

import com.crud.msstudent.models.Student;
import com.crud.msstudent.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
//import org.zalando.problem.ProblemModule;
//import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @BeforeEach
	void setUp() {
		this.studentList = Lists.newArrayList(
				Student.builder().firstname("rajib").lastname("samanta").email("abc@gmail.com").build(),
				Student.builder().firstname("locahn").lastname("samanta").email("xyz@gmail.com").build());

		//objectMapper.registerModule(new ProblemModule());
		//objectMapper.registerModule(new ConstraintViolationProblemModule());
	}

    @Test
    void shouldFetchAllStudents() throws Exception {
        given(studentService.getAllStudents()).willReturn(this.studentList);

        this.mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(studentList.size())));
    }

    @Test
    void shouldFindStudentById() throws Exception {
        int userId = 1;
        Student student = Student.builder().firstname("rajib").lastname("samanta").email("abc@gmail.com").build();
        given(studentService.findById(userId)).willReturn(Optional.of(student));

        this.mockMvc.perform(get("/api/students/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is(student.getEmail())))
                .andExpect(jsonPath("$.firstname", is(student.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(student.getLastname())))
        ;
    }

    @Test
    void shouldReturn404WhenFetchingNonExistingStudent() throws Exception {
        int userId = 1;
        given(studentService.findById(userId)).willReturn(Optional.empty());

        this.mockMvc.perform(get("/api/students/{id}", userId))
                .andExpect(status().isNotFound());

    }

	
	/*
	 * @Test void shouldCreateNewStudent() throws Exception {
	 * given(studentService.save(any(Student.class))).willAnswer((invocation) ->
	 * invocation.getArgument(0));
	 * 
	 * Student student = new Student(3, "pwd", "Name", "newuser1@gmail.com");
	 * this.mockMvc
	 * .perform(post("/api/students").contentType(MediaType.APPLICATION_JSON_UTF8)
	 * .content(objectMapper.writeValueAsString(student)))
	 * .andExpect(status().isCreated()).andExpect(jsonPath("$.email",
	 * is(student.getEmail()))) .andExpect(jsonPath("$.password",
	 * is(student.getFirstname()))) .andExpect(jsonPath("$.name",
	 * is(student.getLastname())));
	 * 
	 * }
	 */
	 

	
    @Test
    void shouldReturn404WhenUpdatingNonExistingStudent() throws Exception {
        int userId = 1;
        given(studentService.findById(userId)).willReturn(Optional.empty());
        Student student = new Student(userId,"firstname", "lastname","user1@gmail.com");

        this.mockMvc.perform(put("/api/students/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isNotFound());

    }

	/*
	 * @Test void shouldDeleteStudent() throws Exception { int userId = 1; Student
	 * student = new Student(userId, "pwd", "Name", "user1@gmail.com");
	 * given(studentService.findById(userId)).willReturn(Optional.of(student));
	 * doNothing().when(studentService).deleteById(student.getId());
	 * 
	 * this.mockMvc.perform(delete("/api/students/{id}", student.getId()))
	 * .andExpect(status().isOk()) .andExpect(jsonPath("$.email",
	 * is(student.getEmail()))) .andExpect(jsonPath("$.firstname",
	 * is(student.getFirstname()))) .andExpect(jsonPath("$.lastname",
	 * is(student.getLastname())));
	 * 
	 * }
	 */

    @Test
    void shouldReturn404WhenDeletingNonExistingStudent() throws Exception {
        int userId = 1;
        given(studentService.findById(userId)).willReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/students/{id}", userId))
                .andExpect(status().isNotFound());

    }

}
