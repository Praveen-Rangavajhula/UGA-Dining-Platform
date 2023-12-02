package com.db.project.ugadining.controller;

import com.db.project.ugadining.model.Student;
import com.db.project.ugadining.model.dto.StudentDto;
import com.db.project.ugadining.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger( StudentController.class );
    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {

        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Student getStudentByName(@PathVariable Long studentId) {

        return studentService.getStudentById(studentId);
    }

    @GetMapping(path = "/name/{studentName}")
    public Student getStudentByName(@PathVariable String studentName) {

        return studentService.getStudentByName(studentName);
    }

    @GetMapping(path = "/email/{studentEmail}")
    public Student getStudentByEmail(@PathVariable String studentEmail) {

        return studentService.getStudentByEmail(studentEmail);
    }

    @PostMapping
    public ResponseEntity<Student> postStudent(
            @RequestBody StudentDto studentDto
    ) {

        Student student = studentService.putNewStudent(studentDto);
        logger.info("Successfully registered student {} ", student.getStudentName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudentById (
            @PathVariable Long studentId
    ) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok("Deleted student with ID: " + studentId);
    }

    @DeleteMapping(path = "/name/{studentName}")
    public ResponseEntity<String> deleteStudentByName (
            @PathVariable String studentName
    ) {
        studentService.deleteStudentByName(studentName);
        return ResponseEntity.ok("Deleted student with NAME: " + studentName);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId,
            @RequestBody StudentDto studentDto
    ) {
        Student student = studentService.updateStudent(studentId, studentDto);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
