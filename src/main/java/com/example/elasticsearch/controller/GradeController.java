package com.example.elasticsearch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.elasticsearch.model.Grade;
import com.example.elasticsearch.repository.GradeRepository;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    @Autowired
    private GradeRepository repository;
    @PostMapping
    public Grade create(@RequestBody Grade grade) {
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
