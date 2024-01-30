package com.example.dbdemo.service;

import com.example.dbdemo.model.Faculty;
import com.example.dbdemo.repository.FacultyRepository;
import com.example.dbdemo.utilities.FilterUtils;
import com.example.dbdemo.utilities.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    private FilterUtils<Faculty> filterUtil = new FilterUtils<>();

    @Override
    public List<Faculty> getAllFaculties(String name, Gender gender, String sort) {
        List<Faculty> faculties = this.filterUtil.filterList(facultyRepository.findAll(), Faculty::isActive);
        if (sort != null && !sort.isEmpty()) {
            faculties = filterUtil.filterList(facultyRepository.findAll(Sort.by(sort)), Faculty::isActive);
        }
        if (name != null || gender != null) {
            faculties = facultyRepository.findFilteredFaculties(name, gender);
        }
        return faculties;
    }

    @Override
    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Faculty> getFacultiesByIds(List<Long> ids) {
        if (ids.isEmpty())
            return this.filterUtil.filterList(facultyRepository.findAll(), Faculty::isActive);

        return this.filterUtil.filterList(facultyRepository.findAllById(ids), Faculty::isActive);
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        Long id = faculty.getId();
        if (facultyRepository.existsById(id)) {
            faculty.setId(id);
            return facultyRepository.save(faculty);
        } else {
            return null;
        }
    }

    @Override
    public Boolean deleteFaculty(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElse(null);
        if (faculty != null) {
            faculty.setActive(false);
            facultyRepository.save(faculty);
            return true;
        }
        return false;
    }
}
