package com.suprised.dubbo.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class EventServiceTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();
        
        EventService service = (EventService) context.getBean("eventService");
        
        System.out.println(service.invoca("执行方法参数"));
        Thread.sleep(1000);
        try {
            System.out.println(service.invocaThrowException("异常方法参数"));
        } catch (Exception e) {
            System.out.println("catch exception");
        }
    }

}
