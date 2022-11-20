package com.example.universitysystem.student;

import com.example.universitysystem.exception.StudentNotFoundException;
import com.example.universitysystem.exception.TeacherNotFoundException;
import com.example.universitysystem.student.dto.StudentDto;
import com.example.universitysystem.student.dto.StudentMapper;
import com.example.universitysystem.student.dto.request.AssignStudentToTeacherRequest;
import com.example.universitysystem.student.dto.request.CreateUpdateStudentRequest;
import com.example.universitysystem.teacher.Teacher;
import com.example.universitysystem.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentMapper studentMapper;

    public StudentDto createStudent(CreateUpdateStudentRequest createUpdateStudentRequest) {
        Student student = studentMapper.mapToStudent(createUpdateStudentRequest);
        student = studentRepository.save(student);
        return studentMapper.mapToStudentDto(student);
    }

    public StudentDto updateStudent(long studentId, CreateUpdateStudentRequest createUpdateStudentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        student.setFirstName(createUpdateStudentRequest.getFirstName());
        student.setLastName(createUpdateStudentRequest.getLastName());
        student.setEmail(createUpdateStudentRequest.getEmail());
        student.setAge(createUpdateStudentRequest.getAge());
        student.setFieldOfStudy(createUpdateStudentRequest.getFieldOfStudy());

        student = studentRepository.save(student);
        return studentMapper.mapToStudentDto(student);
    }

    @Transactional
    public void deleteStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);

        Set<Teacher> teachers = student.getTeachers();
        for (Teacher teacher : teachers) {
            teacher.getStudents().removeIf(s -> s.getId().equals(id));
        }
        teacherRepository.saveAll(teachers);
        studentRepository.deleteById(id);
    }

    public void assignStudentToTeacher(AssignStudentToTeacherRequest assignStudentToTeacherRequest) {
        Teacher teacher = teacherRepository.findById(assignStudentToTeacherRequest.getTeacherId())
                .orElseThrow(TeacherNotFoundException::new);
        Student student = studentRepository.findById(assignStudentToTeacherRequest.getStudentId())
                .orElseThrow(StudentNotFoundException::new);

        Set<Student> students = teacher.getStudents();
        if (students.stream().noneMatch(s -> s.getId().equals(student.getId()))) {
            students.add(student);
            teacherRepository.save(teacher);
        }
    }

    public void removeStudentFromTeacher(AssignStudentToTeacherRequest assignStudentToTeacherRequest) {
        Teacher teacher = teacherRepository.findById(assignStudentToTeacherRequest.getTeacherId())
                .orElseThrow(TeacherNotFoundException::new);
        Student student = studentRepository.findById(assignStudentToTeacherRequest.getStudentId())
                .orElseThrow(StudentNotFoundException::new);


        Set<Student> students = teacher.getStudents();
        if (students.stream().anyMatch(s -> s.getId().equals(student.getId()))) {
            students.removeIf(s -> s.getId().equals(student.getId()));
            teacherRepository.save(teacher);
        }
    }

    public List<StudentDto> getAllStudents(int page, int size, boolean ascendingSort) {
        Sort sort = Sort.by("id");
        if (ascendingSort) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        return studentRepository.findAll(PageRequest.of(page, size, sort))
                .stream()
                .map(studentMapper::mapToStudentDto)
                .toList();
    }

    public List<StudentDto> getStudentsByTeacherId(long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(TeacherNotFoundException::new)
                .getStudents().stream()
                .map(studentMapper::mapToStudentDto)
                .toList();
    }

    public List<StudentDto> getStudentsByFirstName(String firstName) {
        return studentRepository.findAllByFirstName(firstName)
                .stream()
                .map(studentMapper::mapToStudentDto)
                .toList();
    }

    public List<StudentDto> getStudentsByLastName(String lastName) {
        return studentRepository.findAllByLastName(lastName)
                .stream()
                .map(studentMapper::mapToStudentDto)
                .toList();
    }

}
