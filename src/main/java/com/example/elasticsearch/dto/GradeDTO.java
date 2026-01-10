package com.example.elasticsearch.dto;

import com.example.elasticsearch.model.Grade;

import lombok.Data;

@Data
public class GradeDTO {
    private String name;
    private String description;
    private String curriculumId;
    public Grade toGrade(){
        return Grade.of(name, description, null);
    }
}
