package com.example.restfulapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public Optional<Student> getStudentById(Long id){
        return studentRepository.findById(id);
    }
    public void addStudent(Student student){
        Optional<Student> studentToAdd = studentRepository.findStudentByEmail(student.getEmail());
        if(studentToAdd.isPresent()){
            throw new IllegalStateException("Student already presented");
        } else{
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId){
        if(studentRepository.existsById(studentId)){
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalStateException("Student not found");
        }
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email, LocalDate dob){
        if(studentRepository.existsById(studentId)){
            Student s = studentRepository.findById(studentId).get();
            if(s.getName() != name && name != null ){
                s.setName(name);
            }
            if(s.getEmail() != email && email != null && !studentRepository.findStudentByEmail(email).isPresent() ){
                s.setEmail(email);
            }
            if(s.getDob() != dob && dob != null ){
                s.setDob(dob);
            }
        } else {
            throw new IllegalStateException("Student not found");
        }
    }
}
