package com.cubic.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cubic.entities.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer>{
	
//	List<StudentEntity> findByEmailAndPhone(String emailAddress, String phone);
	
	StudentEntity save(Optional<StudentEntity>studentEntity);

}
