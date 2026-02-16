package com.RestFullAPI.BuildRestAPI.Service;

import com.RestFullAPI.BuildRestAPI.DTO.AddStudentReqestDTO;
import com.RestFullAPI.BuildRestAPI.DTO.StudentDTO;

import java.util.List;
import java.util.Map;

/************************************************************************
 Developer Name : Harshvardhan Vathare
 Developer Content : harsh1234vathare@gmail.com
 Created On : 12/26/2025 10:30 PM
 Project Name : BuildRestAPI
 ************************************************************************/
public interface StudentService   {
     List<StudentDTO> getAllStudents();

     StudentDTO getStudentById(long id);

     StudentDTO creatNewStudent(AddStudentReqestDTO addStudentReqestDTO);

     void deleteStudent(long id);

     StudentDTO updateStudent(long id, AddStudentReqestDTO addStudentReqestDTO);

     StudentDTO updatePartialStudent(long id, Map<String, Object> updates);
}
