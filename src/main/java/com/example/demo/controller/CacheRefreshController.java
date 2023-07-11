package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CacheRefreshService;

@RestController
public class CacheRefreshController {
	
	@Autowired
	private CacheRefreshService cacheRefreshService;
	
	@GetMapping("/refreshCache")
	public void refreshCache() {
		cacheRefreshService.refreshAllCaches();
	}

}
