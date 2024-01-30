package com.example.dbdemo.repository;

import com.example.dbdemo.model.Student;
import com.example.dbdemo.utilities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE (:name IS NULL OR s.name LIKE %:name%) AND (:gender IS NULL OR s.gender = :gender) AND s.isActive = true")
    List<Student> findFilteredStudents(@Param("name") String name, @Param("gender") Gender gender);
}

