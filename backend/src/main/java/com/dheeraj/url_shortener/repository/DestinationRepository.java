package com.dheeraj.url_shortener.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dheeraj.url_shortener.entity.Destination;    


public interface DestinationRepository extends JpaRepository<Destination,UUID>{

    Optional<Destination>  findByNormalizedUrl(String normalizedUrl);
}
