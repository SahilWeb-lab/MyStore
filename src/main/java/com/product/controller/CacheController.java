package com.product.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;

import com.product.endpoints.CacheEndpoint;
import com.product.service.CacheManagerService;
import com.product.util.CommonUtils;

@RestController
public class CacheController implements CacheEndpoint {

	@Autowired
	private CacheManagerService cacheManagerService;
	
	@Override
	public ResponseEntity<?> getAllCache() {
		Collection<String> cache = cacheManagerService.getCache();
		
		if(CollectionUtils.isEmpty(cache))
			return ResponseEntity.noContent().build();
		
		return CommonUtils.createBuildResponse(cache, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCacheByName(String cacheName) {
		Cache cache = cacheManagerService.getCache(cacheName);
		return CommonUtils.createBuildResponse(cache, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> removeAllCache() {
		cacheManagerService.removeAllCache();
		return CommonUtils.createBuildResponseMessage("Cache Removed Successfully!", HttpStatus.OK);
	}

	
	
}
