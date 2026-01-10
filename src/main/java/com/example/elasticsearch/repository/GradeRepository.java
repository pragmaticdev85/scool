package com.example.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.elasticsearch.model.Grade;
public interface GradeRepository extends ElasticsearchRepository<Grade, String> {
}
