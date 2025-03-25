package com.example.elasticsearch.repository;

import com.example.elasticsearch.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
}
