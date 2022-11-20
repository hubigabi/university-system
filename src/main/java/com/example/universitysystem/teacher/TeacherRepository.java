package com.example.universitysystem.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByFirstName(String firstName);

    List<Teacher> findAllByLastName(String lastName);

}