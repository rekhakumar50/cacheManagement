package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@Service
public class CacheRefreshService {
	
	@Autowired
    private CacheManager cacheManager;
	
	@Autowired
	private DepartmentService departmentService;
	

    public void refreshAllCaches() {
    	//removing the cache
    	cacheManager.getCache("departmentCache").removeAll();
    	
    	//create cache
    	departmentService.getDepartments();
    }
    
    public void removeCache(String cacheName, List<String> key) {
        cacheManager.removeCache(cacheName);
    }
    
    public void removeCacheElements(String cacheName, List<String> key) {
        Ehcache cache = cacheManager.getCache(cacheName);

        if (cache.isKeyInCache(key)) {
            cache.remove(key);
        }
        //refer below link for programmatic cache
        //https://dev.to/crmepham/programmatic-access-to-ehcache-in-spring-boot-1oci
    }
    
    private void addOrUpdateCacheElements() {
    	cacheManager.getCache("departmentCache").put(new Element("departmentList", new ArrayList<>()));
    }

    @Scheduled(fixedRate = 6000)
    public void refreshAllcachesAtIntervals() {
        refreshAllCaches();
    }

}
