package com.example.universitysystem.student;

import com.example.universitysystem.student.dto.StudentDto;
import com.example.universitysystem.student.dto.request.AssignStudentToTeacherRequest;
import com.example.universitysystem.student.dto.request.CreateUpdateStudentRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody CreateUpdateStudentRequest createUpdateStudentRequest) {
        StudentDto studentDto = studentService.createStudent(createUpdateStudentRequest);
        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable long id,
                                                    @RequestBody CreateUpdateStudentRequest createUpdateStudentRequest) {
        StudentDto studentDto = studentService.updateStudent(id, createUpdateStudentRequest);
        return ResponseEntity.ok(studentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/assignStudentToTeacher")
    public ResponseEntity<Void> assignStudentToTeacher(@RequestBody AssignStudentToTeacherRequest assignStudentToTeacherRequest) {
        studentService.assignStudentToTeacher(assignStudentToTeacherRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/removeStudentFromTeacher")
    public ResponseEntity<Void> removeStudentFromTeacher(@RequestBody AssignStudentToTeacherRequest assignStudentToTeacherRequest) {
        studentService.removeStudentFromTeacher(assignStudentToTeacherRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "100") int size,
                                                           @RequestParam(defaultValue = "true") boolean ascendingSort) {
        List<StudentDto> students = studentService.getAllStudents(page, size, ascendingSort);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/byTeacherId/{teacherId}")
    public ResponseEntity<List<StudentDto>> getStudentsByTeacherId(@PathVariable long teacherId) {
        List<StudentDto> studentsByTeacherId = studentService.getStudentsByTeacherId(teacherId);
        return ResponseEntity.ok(studentsByTeacherId);
    }

    @GetMapping("/byFirstName/{firstName}")
    public ResponseEntity<List<StudentDto>> getStudentsByFirstName(@PathVariable String firstName) {
        List<StudentDto> studentsByFirstName = studentService.getStudentsByFirstName(firstName);
        return ResponseEntity.ok(studentsByFirstName);
    }

    @GetMapping("/byLastName/{lastName}")
    public ResponseEntity<List<StudentDto>> getStudentsByLastName(@PathVariable String lastName) {
        List<StudentDto> studentsByLastName = studentService.getStudentsByLastName(lastName);
        return ResponseEntity.ok(studentsByLastName);
    }

}
