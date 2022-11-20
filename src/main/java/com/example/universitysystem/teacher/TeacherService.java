package com.example.universitysystem.teacher;

import com.example.universitysystem.exception.StudentNotFoundException;
import com.example.universitysystem.exception.TeacherNotFoundException;
import com.example.universitysystem.student.StudentRepository;
import com.example.universitysystem.teacher.dto.TeacherDto;
import com.example.universitysystem.teacher.dto.TeacherMapper;
import com.example.universitysystem.teacher.dto.request.CreateUpdateTeacherRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherMapper teacherMapper;

    public TeacherDto createTeacher(CreateUpdateTeacherRequest createUpdateTeacherRequest) {
        Teacher teacher = teacherMapper.mapToTeacher(createUpdateTeacherRequest);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.mapToTeacherDto(teacher);
    }

    public TeacherDto updateTeacher(long teacherId, CreateUpdateTeacherRequest createUpdateTeacherRequest) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(TeacherNotFoundException::new);
        teacher.setFirstName(createUpdateTeacherRequest.getFirstName());
        teacher.setLastName(createUpdateTeacherRequest.getLastName());
        teacher.setEmail(createUpdateTeacherRequest.getEmail());
        teacher.setAge(createUpdateTeacherRequest.getAge());
        teacher.setSubject(createUpdateTeacherRequest.getSubject());

        teacher = teacherRepository.save(teacher);
        return teacherMapper.mapToTeacherDto(teacher);
    }

    @Transactional
    public void deleteTeacherById(long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(TeacherNotFoundException::new);
        teacher.getStudents().clear();

        teacherRepository.save(teacher);
        teacherRepository.deleteById(id);
    }

    public List<TeacherDto> getAllTeachers(int page, int size, boolean ascendingSort) {
        Sort sort = Sort.by("id");
        if (ascendingSort) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        return teacherRepository.findAll(PageRequest.of(page, size, sort))
                .stream()
                .map(teacherMapper::mapToTeacherDto)
                .toList();
    }

    public List<TeacherDto> getTeachersByStudentId(long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new)
                .getTeachers().stream()
                .map(teacherMapper::mapToTeacherDto)
                .toList();
    }

    public List<TeacherDto> getTeachersByFirstName(String firstName) {
        return teacherRepository.findAllByFirstName(firstName)
                .stream()
                .map(teacherMapper::mapToTeacherDto)
                .toList();
    }

    public List<TeacherDto> getTeachersByLastName(String lastName) {
        return teacherRepository.findAllByLastName(lastName)
                .stream()
                .map(teacherMapper::mapToTeacherDto)
                .toList();
    }
}
