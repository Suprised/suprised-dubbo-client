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
        
        // �ӿ��ڵ��õ�ʱ����и��أ����ݸ����㷨��ѡ��һ���ṩ�߽��е��ã����ʧ�ܣ���ѡ����һ����
        HelloworldService demoService = (HelloworldService) context.getBean("helloworldService"); // ��ȡԶ�̷������
        
        for (int i=0; i<100; i++) {
            String result = demoService.sayHi("hello world dubbo." + i); // ִ��Զ�̷���
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
