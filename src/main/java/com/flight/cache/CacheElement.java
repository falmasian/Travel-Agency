package com.flight.cache;

import java.util.Date;

public class CacheElement<T> {

    private final T value;
    private final Date creationTime = new Date();

    public CacheElement(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public long getCreationTime() {
        return creationTime.getTime();
    }
}