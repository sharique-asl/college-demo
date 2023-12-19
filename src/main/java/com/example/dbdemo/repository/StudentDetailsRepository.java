package com.example.dbdemo.repository;

import com.example.dbdemo.model.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {

}
