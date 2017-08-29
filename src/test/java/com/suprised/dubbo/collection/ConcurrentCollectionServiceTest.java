package com.suprised.dubbo.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 并行集合在多个消费者的情况下使用，是否存在并发问题 
 */
@Component
public class ConcurrentCollectionServiceTest {

    private static ConcurrentCollectionService service1;
    private static ConcurrentCollectionService service2;
    private static ConcurrentCollectionService service3;
    private static ConcurrentCollectionService service4;
    private static ConcurrentCollectionService service5;
    
    private static AtomicLong index = new AtomicLong(0);
    
    public static void main(String[] args) {
        service1 = (ConcurrentCollectionService) getApplicationContext().getBean("concurrentCollectionService");
        service2 = (ConcurrentCollectionService) getApplicationContext().getBean("concurrentCollectionService");
        service3 = (ConcurrentCollectionService) getApplicationContext().getBean("concurrentCollectionService");
        service4 = (ConcurrentCollectionService) getApplicationContext().getBean("concurrentCollectionService");
        service5 = (ConcurrentCollectionService) getApplicationContext().getBean("concurrentCollectionService");
        
        final CountDownLatch latch = new CountDownLatch(1);
        // 准备线程
        prepareThrad(latch, service1);
        prepareThrad(latch, service2);
        prepareThrad(latch, service3);
        prepareThrad(latch, service4);
        prepareThrad(latch, service5);
        
        // 启动线程
        latch.countDown();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        ConcurrentHashMap<String, String> map = service1.getAllMap();
        ConcurrentLinkedQueue<String> queue = service2.getAllQueue();
        
        System.out.println("mapSize: " + map.size() + ",queueSize: " + queue.size());
        
        System.out.println(map);
        System.out.println("--------------------------------------------------");
        System.out.println(queue);
    }
    
    public static void prepareThrad(final CountDownLatch latch, final ConcurrentCollectionService service) {
        for (int i=0; i<60; i++) { // 五个消费者，每个消费者同时启动60个线程
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long index1 = index.addAndGet(1);
                    service.put("key:" + index1, "value:" + index1);
                    service.add("value:" + index1);
                    
                    
                }
            }).start();
        }
    }
    
    public static ApplicationContext getApplicationContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();
        return context;
    }
}
