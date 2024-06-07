package com.example.springjwtsecurity.student;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student with that ID does not exist"));
    }

    public void createStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new NoSuchElementException("Student with ID " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, Student updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException("Student with ID " + studentId + " does not exist"));

        if (updatedStudent.getName() != null && !updatedStudent.getName().isEmpty()) {
            student.setName(updatedStudent.getName());
        }
        if (updatedStudent.getEmail() != null && !updatedStudent.getEmail().isEmpty()) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(updatedStudent.getEmail());
            if (studentOptional.isPresent() && !studentOptional.get().getId().equals(studentId)) {
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(updatedStudent.getEmail());
        }
    }
}
