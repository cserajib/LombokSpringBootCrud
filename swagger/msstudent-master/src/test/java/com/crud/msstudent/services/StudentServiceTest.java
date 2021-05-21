package com.crud.msstudent.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.msstudent.models.Student;
import com.crud.msstudent.repositories.StudentRepository;

@RunWith(SpringRunner.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentService studentService;
	
	@Test
	public void findAllEntity() {
		List<Student> data = Lists.list(Student.builder().id(1).firstname("abc").lastname("xyz").email("abc@gmail.com").build());
		when(studentRepository.findAll()).thenReturn(data);
		
		List<Student> result = studentService.getAllStudents();
		assertEquals(result.size(), 1);
	}
	
	
}