package com.flightagency.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager<T, U> {

    private final Map<String, Cache> manager = new HashMap<>();


    public void createCache(String cacheName, long periodTime) {
        if (!isCacheExist(cacheName)) {
            manager.put(cacheName, getCacheWithConfig(periodTime));
        }
    }

    private Cache getCacheWithConfig(long periodTime) {
        return new Cache(periodTime);
    }

    public boolean isCacheExist(String cacheName) {
        return getCacheWithName(cacheName) != null;
    }

    public Cache getCacheWithName(String cacheName) {
        return manager.get(cacheName);
    }

    public boolean isKeyInCache(String cacheName, T key) {
        if (key == null || cacheName == null || getCacheWithName(cacheName) == null) {
            return false;
        }
        return getItemFromCache(cacheName, key) != null;
    }

    @SuppressWarnings("unchecked")
    public U getItemFromCache(String cacheName, T key) {
        if (key == null) {
            return null;
        }
        Cache cache = getCacheWithName(cacheName);
        if (cache != null && cache.getElementFromCache(key) != null) {
            CacheElement<U> element = (CacheElement<U>) (cache.getElementFromCache(key));
            return element != null ? element.getValue() : null;
        }
        return null;
    }

    public void removeItemFromCache(String cacheName, T key) {
        Cache cache = getCacheWithName(cacheName);
        if (cache != null) {
            cache.removeFromCache(key);
        }
    }

    public void removeCache(String cacheName) {
        getCacheWithName(cacheName).removeAll();
        manager.remove(cacheName);
    }

    public boolean putInCacheWithName(String cacheName, Object key, Object value) {
        Cache cache = getCacheWithName(cacheName);
        boolean result = false;
        if (cache != null) {
            result = cache.putElementInCache(key, value);
        }
        return result;
    }
}