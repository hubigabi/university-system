package com.example.universitysystem;

import com.example.universitysystem.student.Student;
import com.example.universitysystem.student.StudentRepository;
import com.example.universitysystem.teacher.Teacher;
import com.example.universitysystem.teacher.TeacherRepository;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@Log4j2
public class UniversitySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversitySystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        return args -> {
            Faker faker = new Faker();

            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                String name = getName(faker);
                String lastName = faker.name().lastName();
                String email = name + lastName + "@university.edu.com";
                int age = ThreadLocalRandom.current().nextInt(19, 26);
                Student student = new Student(0L, name, lastName, age, email, faker.lorem().word(), Collections.emptySet());
                students.add(student);
            }
            students = studentRepository.saveAll(students);

            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                int age = ThreadLocalRandom.current().nextInt(35, 65);
                Set<Student> teacherStudents = new HashSet<>();
                for (int j = 0; j < 30; j++) {
                    int studentId = ThreadLocalRandom.current().nextInt(0, students.size());
                    teacherStudents.add(students.get(studentId));
                }

                String name = getName(faker);
                String lastName = faker.name().lastName();
                String email = name + lastName + "@university.edu.com";
                Teacher teacher = new Teacher(0L, name, lastName, age, email, faker.lorem().word(), teacherStudents);
                teachers.add(teacher);
            }

            teacherRepository.saveAll(teachers);
            log.info("Finished initializing data");
        };
    }

    private String getName(Faker faker) {
        StringBuilder name = new StringBuilder(faker.name().firstName());
        while (name.length() < 3) {
            name.append("a");
        }
        return name.toString();
    }
}