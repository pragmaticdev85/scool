package com.example.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(indexName = "curriculum")
public class Curriculum {
    @Id
    private String id;
    private String name;
    private String description;
    private String board;
    private String pointOfContact;
    @Field(type = FieldType.Nested, includeInParent = true)
    private ContactAddress contactAddress;

    public static Curriculum of(final String id, final String name, final String description, 
        final String board, final String pointOfContact, final ContactAddress contactAddress) {
        return new Curriculum(id, name, description, board, pointOfContact, contactAddress);
    }
}
