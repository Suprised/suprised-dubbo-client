package com.suprised.dubbo.group;

/**
 * ��һ�������ж��ʵ��ʱ��������goup��������
 */
public interface ServerGroup {

    public String operateSystemCommad();
    
    /**
     * ���Ʋ���������linux������20��windows������10 
     */
    public String limitAccess() throws LimitException;
}
