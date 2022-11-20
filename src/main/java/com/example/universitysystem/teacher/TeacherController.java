package com.example.universitysystem.teacher;

import com.example.universitysystem.teacher.dto.TeacherDto;
import com.example.universitysystem.teacher.dto.request.CreateUpdateTeacherRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@CrossOrigin
@AllArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDto> createTeacher(@RequestBody CreateUpdateTeacherRequest createUpdateTeacherRequest) {
        TeacherDto teacherDto = teacherService.createTeacher(createUpdateTeacherRequest);
        return ResponseEntity.ok(teacherDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable long id,
                                                    @RequestBody CreateUpdateTeacherRequest createUpdateTeacherRequest) {
        TeacherDto teacherDto = teacherService.updateTeacher(id, createUpdateTeacherRequest);
        return ResponseEntity.ok(teacherDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacherById(@PathVariable long id) {
        teacherService.deleteTeacherById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "100") int size,
                                                           @RequestParam(defaultValue = "true") boolean ascendingSort) {
        List<TeacherDto> teachers = teacherService.getAllTeachers(page, size, ascendingSort);
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/byStudentId/{studentId}")
    public ResponseEntity<List<TeacherDto>> getTeachersByStudentId(@PathVariable long studentId) {
        List<TeacherDto> teachersByStudentId = teacherService.getTeachersByStudentId(studentId);
        return ResponseEntity.ok(teachersByStudentId);
    }

    @GetMapping("/byFirstName/{firstName}")
    public ResponseEntity<List<TeacherDto>> getTeachersByFirstName(@PathVariable String firstName) {
        List<TeacherDto> teachersByFirstName = teacherService.getTeachersByFirstName(firstName);
        return ResponseEntity.ok(teachersByFirstName);
    }

    @GetMapping("/byLastName/{lastName}")
    public ResponseEntity<List<TeacherDto>> getTeachersByLastName(@PathVariable String lastName) {
        List<TeacherDto> teachersByLastName = teacherService.getTeachersByLastName(lastName);
        return ResponseEntity.ok(teachersByLastName);
    }

}
