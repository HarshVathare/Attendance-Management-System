package com.RestFullAPI.BuildRestAPI.Controller;

import com.RestFullAPI.BuildRestAPI.DTO.AddStudentReqestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.StudentDTO;
import com.RestFullAPI.BuildRestAPI.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/students")
@Tag(name = "Student APIs", description = "Read, Update and Delete Users")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 01 => First Api Get All Students

    @GetMapping
    @Operation(summary = "Fetch All Students !")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // 02 => Second Api Get Student By Id

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // 03 => Third Api Create new Student

    @PostMapping
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<StudentDTO> createNewStudent(
            @RequestBody @Valid AddStudentReqestDTO addStudentReqestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.creatNewStudent(addStudentReqestDTO));
    }


    // 04 => Fourth Api for Delete Student

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<Void>deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // 05 => Fifth Api for update Student
    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<StudentDTO>updateStudent(@PathVariable long id,
                                                   @RequestBody AddStudentReqestDTO addStudentReqestDTO){
        return ResponseEntity.ok(studentService.updateStudent(id, addStudentReqestDTO));
    }


    // 06 => Sixth Api for Partial upadte
    @PatchMapping("/{id}")
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    public ResponseEntity<StudentDTO>updatePartialStudent(@PathVariable long id,
                                                          @RequestBody Map<String, Object> updates ){
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }



}















