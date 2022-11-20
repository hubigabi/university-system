package com.example.universitysystem.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String fieldOfStudy;
    private Set<Long> teachersId;

}
