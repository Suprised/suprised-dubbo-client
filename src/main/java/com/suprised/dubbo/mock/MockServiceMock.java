package com.suprised.dubbo.mock;

/**
 * 本地伪装：Mock通常用于服务降级，比如某验权服务，当服务提供方全部挂掉后，客户端不抛出异常，而是通过Mock数据返回授权失败。
 */
public class MockServiceMock implements MockService {

    @Override
    public String invocMock(String param) {
        // 该方法只有在出现RpcException时才会出现。
        // 你可以伪造容错数据，此方法只在出现RpcException时被执行
        System.out.println("invocMock方法执行");
        return null;
    }

    @Override
    public String invocMockThrowException(String param) {
        System.out.println("client： invocMockThrowException方法执行");
        return null;
    }

}
