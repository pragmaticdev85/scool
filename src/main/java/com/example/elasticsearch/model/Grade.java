package com.example.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(indexName = "grade")
public class Grade {
    @Id
    private String id;
    private String name;
    private String description;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Curriculum curriculum;

    public static Grade of(final String name, final String description, final Curriculum curriculum) {
        return new Grade(description, name, description, curriculum);
    }
}
