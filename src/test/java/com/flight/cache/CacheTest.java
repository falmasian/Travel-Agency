package com.flight.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CacheTest {

    private  int periodTime;
    @BeforeEach
    public void setup() {
         periodTime = 200;
    }

    @Test
   public void deleteIfExpired(){
       Object key = mock(Object.class);
       Object element = mock(Object.class);
        Date currentTime = new Date();
       when(((CacheElement<?>) element).getCreationTime()).thenReturn(currentTime.getTime() - 4*(10^6));

   }
}
