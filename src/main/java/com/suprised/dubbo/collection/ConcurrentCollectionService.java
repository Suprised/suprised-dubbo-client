package com.suprised.dubbo.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 并行集合在多个消费者的情况下使用，是否存在并发问题 
 */
public interface ConcurrentCollectionService {

    /**
     * ConcurrentHashMap的使用 
     */
    public void put(String key, String value);
    
    public ConcurrentHashMap<String, String> getAllMap();
    
    /**
     * ConcurrentLinkedQueue使用 
     */
    public void add(String value);
    
    public ConcurrentLinkedQueue<String> getAllQueue();
    
}
