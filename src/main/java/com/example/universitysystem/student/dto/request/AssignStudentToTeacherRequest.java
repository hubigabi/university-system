package com.example.universitysystem.student.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignStudentToTeacherRequest {

    private long studentId;
    private long teacherId;
}
