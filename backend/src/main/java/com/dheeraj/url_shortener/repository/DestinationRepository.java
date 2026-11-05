package com.dheeraj.url_shortener.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dheeraj.url_shortener.entity.Destination;    


public interface DestinationRepository extends JpaRepository<Destination,UUID>{

    Optional<Destination>  findByNormalizedUrl(String normalizedUrl);

    @Modifying
    @Query(value= """ 
            INSERT INTO shortener_v2.destinations (id,original_url,normalized_url) VALUES(:id,:originalUrl,:normalizedUrl) ON CONFLICT (normalized_url) DO NOTHING 
            """,nativeQuery = true)
    int insertIfNotExists(@Param("id") UUID id,
                          @Param("originalUrl") String originalUrl,
                          @Param("normalizedUrl") String normalizedUrl
    );

}
