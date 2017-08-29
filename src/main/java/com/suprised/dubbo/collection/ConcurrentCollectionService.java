package com.suprised.dubbo.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ���м����ڶ�������ߵ������ʹ�ã��Ƿ���ڲ������� 
 */
public interface ConcurrentCollectionService {

    /**
     * ConcurrentHashMap��ʹ�� 
     */
    public void put(String key, String value);
    
    public ConcurrentHashMap<String, String> getAllMap();
    
    /**
     * ConcurrentLinkedQueueʹ�� 
     */
    public void add(String value);
    
    public ConcurrentLinkedQueue<String> getAllQueue();
    
}
