package com.dheeraj.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlIdRepository extends JpaRepository<Object, Long> {

    @Query(value = "SELECT nextval('url_id_sequence')", nativeQuery = true)
    Long nextId();

}
