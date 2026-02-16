package com.RestFullAPI.BuildRestAPI.Repository;

import com.RestFullAPI.BuildRestAPI.DTO.StudentDTO;
import com.RestFullAPI.BuildRestAPI.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/************************************************************************
 Developer Name : Harshvardhan Vathare
 Developer Content : harsh1234vathare@gmail.com
 Created On : 12/26/2025 8:19 PM
 Project Name : BuildRestAPI
 ************************************************************************/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
