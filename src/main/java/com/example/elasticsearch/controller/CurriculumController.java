package com.example.elasticsearch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.example.elasticsearch.dto.CurriculumDTO;
import com.example.elasticsearch.model.ContactAddress;
import com.example.elasticsearch.model.Curriculum;
import com.example.elasticsearch.repository.ContactAddressRepository;
import com.example.elasticsearch.repository.CurriculumRepository;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/api/curricula")
public class CurriculumController {
    @Autowired
    private CurriculumRepository repository;

    @Autowired
    private ContactAddressRepository contactAddressRepository;

    @PostMapping
    public Curriculum create(@RequestBody CurriculumDTO curriculumDTO) {
        if (curriculumDTO == null || StringUtils.isBlank(curriculumDTO.getContactAddressId())){
            throw new HttpServerErrorException(HttpStatusCode.valueOf(400), 
                "Empty contact-address-id supplied");
        }
        final Optional<ContactAddress> contactAddress = contactAddressRepository.findById(curriculumDTO.getContactAddressId());
        if (contactAddress.isEmpty()) {
            throw new HttpServerErrorException(HttpStatusCode.valueOf(400), 
                "The supplied contact-address-id doesn't exist");
        }
        Curriculum curriculum = curriculumDTO.toCurriculum();
        curriculum.setContactAddress(contactAddress.get());
        return repository.save(curriculum);
    }
    @GetMapping("/{id}")
    public Optional<Curriculum> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    @GetMapping
    public Iterable<Curriculum> findAll() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Curriculum update(@PathVariable String id, @RequestBody Curriculum curriculum) {
        curriculum.setId(id);
        return repository.save(curriculum);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAll(){
        repository.deleteAll();
    }
}
