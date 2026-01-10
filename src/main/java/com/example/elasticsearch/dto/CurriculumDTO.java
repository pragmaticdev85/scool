package com.example.elasticsearch.dto;

import com.example.elasticsearch.model.Curriculum;

import lombok.Data;

@Data
public class CurriculumDTO {
    private String name;
    private String description;
    private String board;
    private String pointOfContact;
    private String contactAddressId;
    public Curriculum toCurriculum(){
        return Curriculum.of(contactAddressId, name, description, board, pointOfContact, null);
    }
}
