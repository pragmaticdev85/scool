package com.example.elasticsearch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.example.elasticsearch.dto.GradeDTO;
import com.example.elasticsearch.model.Curriculum;
import com.example.elasticsearch.model.Grade;
import com.example.elasticsearch.repository.CurriculumRepository;
import com.example.elasticsearch.repository.GradeRepository;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    @Autowired
    private GradeRepository repository;

    @Autowired
    private CurriculumRepository curriculumRepository;

    @PostMapping
    public Grade create(@RequestBody GradeDTO gradeDTO) {
        if (gradeDTO == null || StringUtils.isBlank(gradeDTO.getCurriculumId())){
            throw new HttpServerErrorException(HttpStatusCode.valueOf(400), 
                "curriculum-id shall not be empty");
        }
        final Optional<Curriculum> curriculum = 
            curriculumRepository.findById(gradeDTO.getCurriculumId());
        if (curriculum.isEmpty()) {
            throw new HttpServerErrorException(HttpStatusCode.valueOf(400), 
                "The supplied curriculum-id doesn't exist");
        }
        final Grade grade = gradeDTO.toGrade();
        grade.setCurriculum(curriculum.get());
        return repository.save(grade);
    }
    @GetMapping("/{id}")
    public Optional<Grade> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping
    public Iterable<Grade> findAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Grade update(@PathVariable String id, @RequestBody Grade grade) {
        grade.setId(id);
        return repository.save(grade);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll(){
        repository.deleteAll();
    }
}
