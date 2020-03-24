package com.cubic.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubic.dtos.StudentDto;
import com.cubic.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/{id}")
	public StudentDto getStudent(@PathVariable("id") Integer id) {

		return studentService.getStudents(id);
	}

	@PostMapping
	public void addStudents(@RequestBody StudentDto studentDto) {
		studentService.addStudents(studentDto);
	}

	@GetMapping
	public List<StudentDto> getAll() {
		return studentService.getAllStudents();
	}

	@PutMapping(value="/student/{id}")
	public void updateBYId(@PathVariable("id") int id, @RequestBody StudentDto studentDto) {

		studentService.updateStudents(id, studentDto);
	}

}
