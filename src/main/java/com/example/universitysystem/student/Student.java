package com.example.universitysystem.student;

import com.example.universitysystem.teacher.Teacher;
import com.example.universitysystem.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    private String fieldOfStudy;

    @ManyToMany(mappedBy = "students")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Teacher> teachers = new HashSet<>();

    public Student(Long id, String firstName, String lastName, int age, String email, String fieldOfStudy, Set<Teacher> teachers) {
        super(id, firstName, lastName, age, email);
        this.fieldOfStudy = fieldOfStudy;
        this.teachers = teachers;
    }

}
