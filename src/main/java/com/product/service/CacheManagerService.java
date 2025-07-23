package com.product.service;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.Cache;

public interface CacheManagerService {

//	Create a method to get all cache:
	public Collection<String> getCache();
	
//	Create a method to get particular cache:
	public Cache getCache(String cacheName);
	
//	Create a method to remove all cache:
	public void removeAllCache();
	
//	Create a method to remove cache by names:
	public void removeCacheByNames(List<String> cacheNames);
	
}
