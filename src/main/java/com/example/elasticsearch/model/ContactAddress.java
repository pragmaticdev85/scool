package com.example.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "contact_address")
public class ContactAddress {
    @Id
    private String id;
    private String lineOne;
    private String lineTwo;
    private String lineThree;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
