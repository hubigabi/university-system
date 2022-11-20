package com.example.universitysystem.student.dto;

import com.example.universitysystem.student.Student;
import com.example.universitysystem.student.dto.request.CreateUpdateStudentRequest;
import com.example.universitysystem.teacher.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student mapToStudent(CreateUpdateStudentRequest createUpdateStudentRequest);

    @Mapping(source = "teachers", target = "teachersId", qualifiedByName = "mapTeachers")
    StudentDto mapToStudentDto(Student student);

    @Named("mapTeachers")
    default Set<Long> mapTeachers(Set<Teacher> teachers) {
        return teachers.stream()
                .map(Teacher::getId)
                .collect(Collectors.toSet());
    }

}
