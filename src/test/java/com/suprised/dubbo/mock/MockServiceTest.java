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
        
        System.out.println(test.getService().invocMock("û���쳣�ĵ���"));
        Thread.sleep(15000);// �Ͽ�����ˣ�ʹ�쳣��ʱ���ִ��mock(ֻ��dubbo�ڲ�����RcpException�Ż����mock���ڲ����������)
        System.out.println(test.getService().invocMockThrowException("�׳�RcpException�ĵ���"));
    }

    public MockService getService() {
        return service;
    }

    public void setService(MockService service) {
        this.service = service;
    }

}
