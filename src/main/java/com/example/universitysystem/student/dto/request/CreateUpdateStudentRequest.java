package com.example.universitysystem.student.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateStudentRequest {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String fieldOfStudy;
}
