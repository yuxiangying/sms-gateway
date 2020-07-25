package com.bs.pro.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ehcache封装实现
 * @author csj
 *
 */
@Service
@Log4j2
public class EhcacheServiceImpl {

	@Autowired
	CacheManager cacheManager;


	public String get(String key,String channel) {
		Cache cache = cacheManager.getCache(channel);
		Element element = cache.get(key);
		if(element!=null)
			return (String) element.getObjectValue();
		return null;
    }

    public void put(String key,String value,String channel) {
    	Cache cache = cacheManager.getCache(channel);
    	cache.put(new Element(key, value));
    }

    public void remove(String key, String channel) {

		cacheManager.getCache(channel).remove(key);
	}


}
