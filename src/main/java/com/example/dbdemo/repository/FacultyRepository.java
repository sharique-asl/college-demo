package com.example.dbdemo.repository;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.utilities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query("SELECT f FROM Faculty f WHERE (:name IS NULL OR f.name LIKE %:name%) AND (:gender IS NULL OR f.gender = :gender) AND f.isActive = true")
    List<Faculty> findFilteredFaculties(@Param("name") String name, @Param("gender") Gender gender);
}
