package com.product.endpoints;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cache APIs", description = "All cache related APIs.")
@RequestMapping("/api/v1/cache")
public interface CacheEndpoint {

	@Operation(summary = "Get All Cache", description = "This API is used to get all the cache.")
	@GetMapping("/")
	public ResponseEntity<?> getAllCache();
	
	@Operation(summary = "Get Cache By Name", description = "This API is used to get cache by name individually.")
	@GetMapping("/{cacheName}")
	public ResponseEntity<?> getCacheByName(@PathVariable String cacheName);
	
	@Operation(summary = "Remove All Cache", description = "This API is used to remove all cache.")
	@DeleteMapping("/")
	public ResponseEntity<?> removeAllCache();
	
}
