package com.dheeraj.urlshortener.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UrlIdRepository extends Repository<Object, Long> {

    @Query(value = "SELECT nextval('url_id_sequence')", nativeQuery = true)
    Long nextId();

}
