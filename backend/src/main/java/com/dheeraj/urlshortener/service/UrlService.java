package com.dheeraj.urlshortener.service;

import org.springframework.stereotype.Service;

import com.dheeraj.urlshortener.dto.CreateUrlRequest;
import com.dheeraj.urlshortener.dto.CreateUrlResponse;

@Service
public class UrlService{
	public String getStatus(){
		return "URl Service is working";
	}
	
	public String getApplicationName(){
		return "URL-Shortener";
	}

	public CreateUrlResponse createShortUrl(CreateUrlRequest request){
		String shortCode = "aB93xkA";
		String shortUrl = "http://localhost:8080/"+shortCode;

		return new CreateUrlResponse(shortCode, shortUrl);
	}
}
