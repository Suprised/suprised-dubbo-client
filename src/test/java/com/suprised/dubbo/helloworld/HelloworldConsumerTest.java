package com.suprised.dubbo.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.suprised.dubbo.helloworld.HelloworldAnnotationService;
import com.suprised.dubbo.helloworld.HelloworldService;

@Component
public class HelloworldConsumerTest {

    @Reference(version = "1.0.0")
    private HelloworldAnnotationService annotationService;
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();
        
        HelloworldConsumerTest consumer = (HelloworldConsumerTest) context.getBean("helloworldConsumerTest");
        System.out.println("1 + 2 = " + consumer.getAnnotationService().calcAdd(1, 2));
        System.out.println("5 - 2 = " + consumer.getAnnotationService().calcDel(5, 2));
        
        // 接口在调用的时候进行负载，根据负载算法，选择一个提供者进行调用，如果失败，则选用另一个。
        HelloworldService demoService = (HelloworldService) context.getBean("helloworldService"); // 获取远程服务代理
        
        for (int i=0; i<100; i++) {
            String result = demoService.sayHi("hello world dubbo." + i); // 执行远程方法
            System.out.println(result);
        }
    }

    public HelloworldAnnotationService getAnnotationService() {
        return annotationService;
    }

    public void setAnnotationService(HelloworldAnnotationService annotationService) {
        this.annotationService = annotationService;
    }
    
}
