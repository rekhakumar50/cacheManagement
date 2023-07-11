package com.example.demo.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class EhCacheConfig extends CachingConfigurerSupport {
	
	@Bean
	public CacheManager ehCacheManager() {
		CacheConfiguration departmentCache = new CacheConfiguration();
		departmentCache.setName("departmentCache");
		departmentCache.setMaxEntriesLocalHeap(1000);
		departmentCache.setTimeToLiveSeconds(30);
		
//		CacheConfiguration teacherStudentCache = new CacheConfiguration();
//		teacherStudentCache.setName("teacherStudentCache");
//		teacherStudentCache.setMaxEntriesLocalHeap(1000);
//		teacherStudentCache.setTimeToLiveSeconds(30);
		
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		//config.addCache(teacherStudentCache);
		config.addCache(departmentCache);
		return CacheManager.newInstance(config);
	}
	
	
	@Bean
	@Override
	public org.springframework.cache.CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

}
