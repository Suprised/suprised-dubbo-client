package com.suprised.dubbo.group;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class GroupTest {
    
    @Reference(version="1.0.0", group="windows", retries=2)
    private ServerGroup windows;
    
    @Reference(version="1.0.0", group="linux", retries=2)
    private ServerGroup linux;
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();
        
        final GroupTest groupTest = (GroupTest) context.getBean("groupTest");
        System.out.println(groupTest.linux.operateSystemCommad());
        System.out.println(groupTest.windows.operateSystemCommad());
        
        final CountDownLatch start = new CountDownLatch(1); // 控制线程同时执行
        
        for (int i=0; i<400; i++) {// 400个线程同时访问时，会导致dubbo的线程池耗尽（目前配置的是threads="100" threadpool="fixed" queues="200",最大支持300并发）
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        start.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(groupTest.windows.limitAccess());
                    } catch(LimitException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("windows-dubbo拒绝服务： 线程池已耗尽。");
                    }
                }
            }, "windows-thread-" + i).start();
            
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        start.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(groupTest.linux.limitAccess());
                    } catch (Exception e) {
                        System.out.println("linux-dubbo拒绝服务： 线程池已耗尽。");
                    }
                }
            }, "linux-thread-" + i).start();
        }
        
        start.countDown();// 开始执行
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerGroup getWindows() {
        return windows;
    }

    public void setWindows(ServerGroup windows) {
        this.windows = windows;
    }

    public ServerGroup getLinux() {
        return linux;
    }

    public void setLinux(ServerGroup linux) {
        this.linux = linux;
    }
}
