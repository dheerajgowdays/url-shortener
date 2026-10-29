package com.dheeraj.url_shortener.service;

import java.util.Optional;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import com.dheeraj.url_shortener.entity.Destination;
import com.dheeraj.url_shortener.repository.DestinationRepository;

@Service 
public class  DestinationService{

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository){
        this.destinationRepository=destinationRepository;
    }

   public Optional<Destination> findExistingDestination(String originalUrl){

        String normalizedUrl=normalizeUrl(originalUrl);
        return destinationRepository.findByNormalizedUrl(normalizedUrl);
   }

   public Destination getOrCreateDestination(String originalUrl){
        if(originalUrl==null || originalUrl.isBlank()) {
            throw new IllegalArgumentException("URL cannot be null or blank");
        }
        String normalizedUrl = normalizeUrl(originalUrl);
        if(!findExistingDestination(normalizedUrl)){
            return new destination(originalUrl,normalizedUrl);
        return findExistingDestination(normalizedUrl);
   }
    
   private String normalizeUrl(String originalUrl){
        if(originalUrl == null || originalUrl.isBlank()){
            throw new IllegalArgumentException("URL cannot be null or blank");
        }

        URI parsedUri;
        try{
            parsedUri = new URI(originalUrl.trim()).normalize();
        }catch(URISyntaxException e){
            throw new IllegalArgumentException("Malformed URL: "+e.getMessage(),e);
        }

        String scheme = parsedUri.getScheme();
        if(scheme == null){
            throw new IllegalArgumentException("URL must contain an explict scheme (e.g., http or https)");
        }

        String lowerScheme = scheme.toLowerCase();
        if(!"http".equals(lowerScheme) && !"https".equals(lowerScheme)){
            throw new IllegalArgumentException("Unsupported Scheme: '"+scheme+"'. only http and https are allowed");
        }

        String host = parsedUri.getHost();
        if(host == null || host.isBlank()){
            throw new IllegalArgumentException("URL must conating Valid Host");
        }

        String lowerHost = host.toLowerCase();
       try {
           URI normalizedUrl = new URI(
                   lowerScheme,
                   parsedUri.getUserInfo(),
                   lowerHost,
                   parsedUri.getPort(),
                   parsedUri.getPath(),
                   parsedUri.getQuery(),
                   parsedUri.getFragment()
           );
           return normalizedUrl.toString();
       } catch (URISyntaxException e) {
           throw new IllegalArgumentException("Failed to construct Normalized Url: " + e.getMessage(), e);
       }
   }
}
