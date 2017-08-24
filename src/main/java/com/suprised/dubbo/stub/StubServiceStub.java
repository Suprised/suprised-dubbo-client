package com.suprised.dubbo.stub;

/**
 * 本地存根代码 : 服务端想在客户端执行代码
 * 
 * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，
 * 比如：做ThreadLocal缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在API中带上Stub，客户端生成Proxy实，会把Proxy通过构造函数传给Stub，然后把Stub暴露组给用户，
 * Stub可以决定要不要去调Proxy。
 */
public class StubServiceStub implements StubService {

    private StubService service;
    
    public StubServiceStub(StubService subService) {
        this.service = subService;
    }
    
    @Override
    public String invoca(String param) {
        // 此代码在客户端执行
        // 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        System.out.println("调用client端存根代码。");
        return service.invoca(param);
    }

}
