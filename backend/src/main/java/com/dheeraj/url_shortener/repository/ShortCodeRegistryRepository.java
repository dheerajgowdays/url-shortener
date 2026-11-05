package com.dheeraj.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dheeraj.url_shortener.entity.ShortCodeRegistry;

@Repository
public interface ShortCodeRegistryRepository extends JpaRepository<ShortCodeRegistry,String>{

    @Query(value="SELECT nextval('shortener_v2.short_code_id_seq')",nativeQuery=true)
    Long getNextSequenceValue();
}
