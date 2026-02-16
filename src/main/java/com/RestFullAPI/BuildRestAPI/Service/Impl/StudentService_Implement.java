package com.RestFullAPI.BuildRestAPI.Service.Impl;


import com.RestFullAPI.BuildRestAPI.DTO.AddStudentReqestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.StudentDTO;
import com.RestFullAPI.BuildRestAPI.Entity.Student;
import com.RestFullAPI.BuildRestAPI.Repository.StudentRepository;
import com.RestFullAPI.BuildRestAPI.Service.StudentService;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service // Write some Business Logic
public class StudentService_Implement implements StudentService {

    //Call the Persistence Layer
    private final StudentRepository studentRepository;

    //Call the Model Config Layer
    private final ModelMapper modelMapper;

    public StudentService_Implement(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        //Convert Student to StudentDTO by using Stream().map() method
        List<StudentDTO> StudentDTOList = students
                .stream()
                .map( student -> new StudentDTO(student.getId(), student.getName(), student.getEmail()))
                .toList();
        return StudentDTOList;
    }


    @Override
    public StudentDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student not found by Id :"+id));
        return modelMapper.map(student, StudentDTO.class);

    }

    @Override
    public StudentDTO creatNewStudent(AddStudentReqestDTO addStudentReqestDTO) {

        Student newstudent = modelMapper.map(addStudentReqestDTO, Student.class);
        Student student = studentRepository.save(newstudent);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudent(long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student does not exist by id ..!");
        }
        studentRepository.deleteById(id);

    }

    @Override
    public StudentDTO updateStudent(long id, AddStudentReqestDTO addStudentReqestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Student does not exist by Id ..!"));
        modelMapper.map(addStudentReqestDTO,student);
        Student newstudent = studentRepository.save(student);
        return modelMapper.map(newstudent, StudentDTO.class);

    }

    @Override
    public StudentDTO updatePartialStudent(long id,  Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Student does not exist by Id ..!"+id));

        updates.forEach((field, value) -> {
                switch (field) {
                    case "name" :
                        student.setName((String) value);
                    break;

                    case "email" :
                        student.setEmail((String) value);
                    break;

                    default:
                        new IllegalArgumentException("Field is not Supported ..!");
                }
        });

        Student saveStudent = studentRepository.save(student);
        return modelMapper.map(saveStudent,StudentDTO.class);
    }

}










