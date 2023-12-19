package com.example.dbdemo.repository;

import com.example.dbdemo.model.FacultyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyDetailsRepository extends JpaRepository<FacultyDetails, Long> {

}
