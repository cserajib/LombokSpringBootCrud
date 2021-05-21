package com.crud.msstudent.repositories;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.crud.msstudent.repositories.StudentRepository;
import com.crud.msstudent.models.Student;

import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Before
	public void setUp() {
		studentRepository.save(Student.builder().id(1).firstname("abc").lastname("xyz").email("abc@gmail.com").build());
	}
	
	@Test
	@Transactional
	public void findAllEntity() {
		
		List<Student> result =  studentRepository.findAll();
		assertEquals(result.size(), 1);
		
	}

}
