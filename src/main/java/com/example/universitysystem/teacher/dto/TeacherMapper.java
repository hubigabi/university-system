package com.example.universitysystem.teacher.dto;

import com.example.universitysystem.student.Student;
import com.example.universitysystem.teacher.Teacher;
import com.example.universitysystem.teacher.dto.request.CreateUpdateTeacherRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    Teacher mapToTeacher(CreateUpdateTeacherRequest createUpdateTeacherRequest);

    @Mapping(source = "students", target = "studentsId", qualifiedByName = "mapStudents")
    TeacherDto mapToTeacherDto(Teacher teacher);

    @Named("mapStudents")
    default Set<Long> mapStudents(Set<Student> students) {
        return students.stream()
                .map(Student::getId)
                .collect(Collectors.toSet());
    }

}
