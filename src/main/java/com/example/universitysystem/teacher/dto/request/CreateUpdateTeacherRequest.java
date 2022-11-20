package com.example.universitysystem.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateTeacherRequest {

    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String subject;
}
