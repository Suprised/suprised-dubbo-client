package com.suprised.dubbo.mock;

/**
 * ����αװ��Mockͨ�����ڷ��񽵼�������ĳ��Ȩ���񣬵������ṩ��ȫ���ҵ��󣬿ͻ��˲��׳��쳣������ͨ��Mock���ݷ�����Ȩʧ�ܡ�
 */
public class MockServiceMock implements MockService {

    @Override
    public String invocMock(String param) {
        // �÷���ֻ���ڳ���RpcExceptionʱ�Ż���֡�
        // �����α���ݴ����ݣ��˷���ֻ�ڳ���RpcExceptionʱ��ִ��
        System.out.println("invocMock����ִ��");
        return null;
    }

    @Override
    public String invocMockThrowException(String param) {
        System.out.println("client�� invocMockThrowException����ִ��");
        return null;
    }

}
