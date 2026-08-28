package com.dheeraj.urlshortener.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dheeraj.urlshortener.dto.CreateUrlRequest;
import com.dheeraj.urlshortener.dto.CreateUrlResponse;
import com.dheeraj.urlshortener.service.UrlService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/urls")
public class HealthController{

	private final UrlService urlService;

	public HealthController(UrlService urlService){
		this.urlService = urlService;
	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public String health(){
		return urlService.getStatus();
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateUrlResponse createUrl(@Valid @RequestBody CreateUrlRequest request){
		return urlService.createShortUrl(request);
	}

}
