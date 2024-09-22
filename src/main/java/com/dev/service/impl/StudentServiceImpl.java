package com.dev.service.impl;

import com.dev.entity.Student;
import com.dev.exception.StudentAlreadyExistsException;
import com.dev.exception.StudentNotFoundException;
import com.dev.repository.StudentRepository;
import com.dev.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    @Override
    public Student addStudent(Student student){

        if(studentAlreadyExists(student.getEmail())){
            throw new StudentAlreadyExistsException(student.getEmail()+" already exists");
        }
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElseThrow(()-> new StudentNotFoundException("Sorry no student found by Id:"+id));
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return repository.findById(id).map(std->{
            std.setFirstName(student.getFirstName());
            std.setLastName(student.getLastName());
            std.setEmail(student.getEmail());
            std.setDepartment(student.getDepartment());
            return repository.save(std);
        }).orElseThrow(()->new StudentNotFoundException("Sorry this student could not be found."));
    }

    @Override
    public String deleteStudent(Long id) {
       if(!repository.existsById(id)){
           throw new StudentNotFoundException("Sorry student not found by Id:"+id);
       }
        repository.deleteById(id);
       return "Student deleted Successfully";
    }

    private boolean studentAlreadyExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
