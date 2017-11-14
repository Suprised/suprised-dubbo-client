package com.suprised.dubbo.mock;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class MockServiceTest {

    @Reference(timeout = 1000, mock="true")
    private MockService service;

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[] { "classpath:applicationContext-client.xml" });
        context.start();

        MockServiceTest test = (MockServiceTest) context.getBean("mockServiceTest");
        
        System.out.println(test.getService().invocMock("没有异常的调用"));
        Thread.sleep(15000);// 断开服务端，使异常的时候的执行mock(只是dubbo内部出现RcpException才会调用mock，内部报错不会调用)
        System.out.println(test.getService().invocMockThrowException("抛出RcpException的调用"));
    }

    public MockService getService() {
        return service;
    }

    public void setService(MockService service) {
        this.service = service;
    }

}
