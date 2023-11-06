package com.flight.cache;

import java.util.*;

public class Cache {

    private final long EXPIRED_TIME_IN_SEC;
    Map<Object, Object> cache;
    Timer timer;

    public Cache(long periodTime) {
        EXPIRED_TIME_IN_SEC = periodTime;
        cache = new HashMap<>();
        timer = new Timer();
        if (periodTime > 0) {
            timer.schedule(new Reminder(), new Date(), EXPIRED_TIME_IN_SEC * 100L);
        }
    }

    public Cache() {
        this(600L);
    }

    class Reminder extends TimerTask {

        public void run() {
            if (EXPIRED_TIME_IN_SEC > 0) {
                clearExpiredElementsFromMap();
            }
        }
    }

    private void clearExpiredElementsFromMap() {
        for (Map.Entry<Object, Object> entry : cache.entrySet()) {
            deleteIfExpired(entry.getKey(), entry.getValue());
        }
    }

    public boolean deleteIfExpired(Object key, Object element) {
        Date currentTime = new Date();
        long diff = currentTime.getTime() - ((CacheElement<?>) element).getCreationTime();

        long seconds = Math.round(diff / 1000.0);

        if (seconds >= EXPIRED_TIME_IN_SEC * 2 / 10) {
            cache.remove(key);
            return true;
        }
        return false;
    }

    public boolean putElementInCache(Object key, Object value) {
        return cache.putIfAbsent(key, value) == null;
    }

    @SuppressWarnings("unchecked")
    public Object getElementFromCache(Object key) {
        if (cache.containsKey(key)) {
            Object current = cache.get(key);
            if (EXPIRED_TIME_IN_SEC > 0) {
                if (deleteIfExpired(key, current)) {
                    return null;
                }
            }
            return current;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void removeFromCache(Object key) {
        cache.remove(key);

    }

    public void removeAll() {
        cache.clear();
    }
}