package com.suprised.dubbo.group;

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
        
        GroupTest groupTest = (GroupTest) context.getBean("groupTest");
        
        System.out.println(groupTest.windows.operateSystemCommad());
        System.out.println(groupTest.linux.operateSystemCommad());
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
