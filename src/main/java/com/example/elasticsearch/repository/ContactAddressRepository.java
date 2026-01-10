package com.example.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.elasticsearch.model.ContactAddress;
public interface ContactAddressRepository extends ElasticsearchRepository<ContactAddress, String> {
    
}
