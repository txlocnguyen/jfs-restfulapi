package com.example.restfulapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getStudents();
    }
    @GetMapping("{studentId}")
    public Optional<Student> getStudentById(@PathVariable("studentId") Long id){
        return studentService.getStudentById(id);
    }
    @PostMapping
    public void registerStudent(@RequestBody Student s){
        studentService.addStudent(s);
    }
    @DeleteMapping("{studentId}")
    public void removeStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
    @PutMapping("{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestParam(required = false) String name,
                              @RequestParam(required =
            false) String email, @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dob){
        studentService.updateStudent(studentId, name, email, dob);
    }
}
