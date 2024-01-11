package com.example.dbdemo.service;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.model.Student;
import com.example.dbdemo.repository.FacultyRepository;
import com.example.dbdemo.utilities.FilterUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    private FilterUtils<Faculty> filterUtil = new FilterUtils<>();

    @Override
    public List<Faculty> getAllFaculties() {
        return this.filterUtil.filterList(facultyRepository.findAll(), Faculty::isActive);
    }

    @Override
    public List<Faculty> getFacultiesByIds(@NotNull List<Long> ids) {

        if (ids.isEmpty())
            return
            this.filterUtil.filterList(facultyRepository.findAll(), Faculty::isActive);

        return this.filterUtil.filterList(facultyRepository.findAllById(ids), Faculty::isActive);
    }

    @Override
    public Faculty createFaculty(@Valid Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(@NotNull @Min(1) Long id, @Valid Faculty faculty) {
        if (facultyRepository.existsById(id)) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        } else {
            // Handle the case where the faculty with the given ID does not exist.
            // You might throw an exception or return a specific response.
            return null;
        }
    }

    public Boolean deleteFaculty(@NotNull @Min(1) Long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if(faculty!=null){
            faculty.setActive(false);
            facultyRepository.save(faculty);
            return true;
        }
        return false;
    }
}
