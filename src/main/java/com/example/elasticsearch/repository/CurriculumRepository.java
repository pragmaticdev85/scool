package com.example.elasticsearch.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.elasticsearch.model.Curriculum;
public interface CurriculumRepository extends ElasticsearchRepository<Curriculum, String> {
} 
