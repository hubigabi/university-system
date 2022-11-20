package com.example.universitysystem.teacher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String subject;
    private Set<Long> studentsId;

}
