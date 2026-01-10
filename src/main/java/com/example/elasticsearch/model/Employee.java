package com.example.elasticsearch.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Document(indexName = "employee")
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String department;
}
