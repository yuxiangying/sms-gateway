package com.bs.pro.config;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * EhCache配置文件，可以替代ehcache.xml 文件
 */

@Configuration
public class EhCacheConfig implements CachingConfigurer {

  @Bean(destroyMethod = "shutdown")
  public net.sf.ehcache.CacheManager ehCacheManager() {
    CacheConfiguration cacheConfiguration = new CacheConfiguration();
    cacheConfiguration.setName("sms");
    cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
    cacheConfiguration.setMaxEntriesLocalHeap(2000);
    cacheConfiguration.setTimeToLiveSeconds(180);

//    CacheConfiguration mailServiceCacheConfiguration = new CacheConfiguration();
//    mailServiceCacheConfiguration.setName("MAIL_SERVICE");
//    mailServiceCacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
//    mailServiceCacheConfiguration.setMaxEntriesLocalHeap(2000);
//    mailServiceCacheConfiguration.setTimeToLiveSeconds(180);

    net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
    //可以创建多个cacheConfiguration，都添加到Config中
    config.addCache(cacheConfiguration);
//    config.addCache(mailServiceCacheConfiguration);
    return net.sf.ehcache.CacheManager.newInstance(config);
  }

  @Bean
  @Override
  public CacheManager cacheManager() {
    return new EhCacheCacheManager(ehCacheManager());
  }

  @Bean
  @Override
  public KeyGenerator keyGenerator() {
    return new SimpleKeyGenerator();
  }

  @Override
  public CacheResolver cacheResolver() {
    return null;
  }

  @Override
  public CacheErrorHandler errorHandler() {
    return null;
  }

}
