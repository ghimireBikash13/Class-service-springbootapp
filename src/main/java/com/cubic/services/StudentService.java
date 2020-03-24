package com.cubic.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cubic.dtos.StudentDto;
import com.cubic.entities.StudentEntity;
import com.cubic.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public StudentDto getStudents(Integer id) {

		Optional<StudentEntity> stud = studentRepository.findById(id);
		StudentDto dto = null;
		if (stud.isPresent()) {
			dto = new StudentDto();
			String firstName = StringUtils.isEmpty(stud.get().getFirstName()) ? " " : stud.get().getFirstName();
			String lastName = StringUtils.isEmpty(stud.get().getLastName()) ? " " : stud.get().getLastName();

			dto.setName(firstName + " " + lastName);
			dto.setEmail(stud.get().getEmail());
			dto.setPhone(stud.get().getPhone());
			dto.setId(stud.get().getId());
		}
		return dto;
	}

	public void addStudents(StudentDto studentDto) {

		StudentEntity studentEntity = new StudentEntity();

		String Name = studentDto.getName();
		int firstSpace = Name.indexOf(" ");
		String firstName = Name.substring(0, firstSpace);
		String lastName = Name.substring(firstSpace).trim();
		studentEntity.setFirstName(firstName);
		studentEntity.setLastName(lastName);
		studentEntity.setEmail(studentDto.getEmail());
		studentEntity.setPhone(studentDto.getPhone());
		studentEntity.setCreatedAt(new Date());
		studentEntity.setUpdated_at(new Date());
		studentEntity.setUuid(UUID.randomUUID().toString());

		studentRepository.save(studentEntity);
	}

	public List<StudentDto> getAllStudents() {
		Iterable<StudentEntity> studentEntity = studentRepository.findAll();

		List<StudentDto> studentDtos = new ArrayList<StudentDto>();

		for (StudentEntity se : studentEntity) {
			StudentDto studentDto = new StudentDto();
			studentDto.setId(se.getId());
			String firstName = StringUtils.isEmpty(se.getFirstName()) ? " " : se.getFirstName();
			String lastName = StringUtils.isEmpty(se.getLastName()) ? " " : se.getLastName();

			studentDto.setName(firstName + " " + lastName);
			studentDto.setEmail(se.getEmail());
			studentDto.setPhone(se.getPhone());

			studentDtos.add(studentDto);

		}
		return studentDtos;
	}

	public void updateStudents(int id, StudentDto studentDto) {

		Optional<StudentEntity> studentEntity = studentRepository.findById(id);

		StudentEntity studentEntity2 = studentEntity.get();
		String Name = studentDto.getName();
		int firstSpace = Name.indexOf(" ");
		String firstName = Name.substring(0, firstSpace);
		String lastName = Name.substring(firstSpace).trim();
		studentEntity2.setFirstName(firstName);
		studentEntity2.setLastName(lastName);
		studentEntity2.setEmail(studentDto.getEmail());
		studentEntity2.setPhone(studentDto.getPhone());
		studentEntity2.setUpdated_at(new Date());
		studentEntity2.setUuid(UUID.randomUUID().toString());
		
		studentRepository.save(studentEntity2);
	}

}
