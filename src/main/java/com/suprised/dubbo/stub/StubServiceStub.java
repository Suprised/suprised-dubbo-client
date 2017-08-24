package com.suprised.dubbo.stub;

/**
 * ���ش������ : ��������ڿͻ���ִ�д���
 * 
 * Զ�̷���󣬿ͻ���ͨ��ֻʣ�½ӿڣ���ʵ��ȫ�ڷ������ˣ����ṩ����Щʱ�����ڿͻ���Ҳִ�в����߼���
 * ���磺��ThreadLocal���棬��ǰ��֤����������ʧ�ܺ�α���ݴ����ݵȵȣ���ʱ����Ҫ��API�д���Stub���ͻ�������Proxyʵ�����Proxyͨ�����캯������Stub��Ȼ���Stub��¶����û���
 * Stub���Ծ���Ҫ��Ҫȥ��Proxy��
 */
public class StubServiceStub implements StubService {

    private StubService service;
    
    public StubServiceStub(StubService subService) {
        this.service = subService;
    }
    
    @Override
    public String invoca(String param) {
        // �˴����ڿͻ���ִ��
        // ������ڿͻ�����ThreadLocal���ػ��棬��Ԥ����֤�����Ƿ�Ϸ����ȵ�
        System.out.println("����client�˴�����롣");
        return service.invoca(param);
    }

}
