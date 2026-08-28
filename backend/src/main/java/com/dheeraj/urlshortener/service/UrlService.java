package com.dheeraj.urlshortener.service;

import com.dheeraj.urlshortener.entity.Url;
import com.dheeraj.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import com.dheeraj.urlshortener.dto.CreateUrlRequest;
import com.dheeraj.urlshortener.dto.CreateUrlResponse;

import java.time.Instant;

@Service
public class UrlService{

	private final UrlRepository urlRepository;

	public UrlService(UrlRepository urlRepository){
		this.urlRepository = urlRepository;
	}
	public String getStatus(){
		return "URl Service is working";
	}

	public String getApplicationName(){
		return "URL-Shortener";
	}

	public CreateUrlResponse createShortUrl(CreateUrlRequest request){
		String shortCode = "aB93xkA";
		Url url = new Url(shortCode,request.url(), Instant.now());
		Url savedUrl = urlRepository.save(url);
		return new CreateUrlResponse(savedUrl.getShortCode(),"http://localhost:8080/"+savedUrl.getShortCode());
	}
}
