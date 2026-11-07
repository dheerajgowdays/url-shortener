package com.dheeraj.url_shortener.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dheeraj.url_shortener.entity.ShortCodeRegistry;
import com.dheeraj.url_shortener.generator.ShortCodeGenerator;
import com.dheeraj.url_shortener.repository.ShortCodeRegistryRepository;


@Service
public class ShortCodeGenerationService{
    private final ShortCodeRegistryRepository shortCodeRegistryRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public ShortCodeGenerationService(ShortCodeRegistryRepository shortCodeRegistryRepository, ShortCodeGenerator shortCodeGenerator){
        this.shortCodeRegistryRepository = shortCodeRegistryRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public String generateShortCode(){
        Long nextId = shortCodeRegistryRepository.getNextSequenceValue();
        if(nextId == null){
            throw new IllegalStateException("Failed to obtain next sequence value form the database");
        }
        return shortCodeGenerator.generate(nextId);
    }
    @Transactional 
    public String reserveNextCode(){
        String shortCode = generateShortCode();
        try {
            ShortCodeRegistry shortCodeRegistry = new ShortCodeRegistry(shortCode);
            shortCodeRegistryRepository.saveAndFlush(shortCodeRegistry);
            return shortCode;
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Could not reserve short code due to a database integrity constraint violation: " + shortCode,
                    ex);
        }
    }
}