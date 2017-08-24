package com.suprised.dubbo.stub;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class StubServiceTest {

    @Reference
    private StubService service;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();

        StubServiceTest test = (StubServiceTest) context.getBean("stubServiceTest");
        System.out.println(test.getService().invoca("调用本地存根代码"));
    }

    public StubService getService() {
        return service;
    }

    public void setService(StubService service) {
        this.service = service;
    }

}
