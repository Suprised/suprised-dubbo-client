package com.suprised.dubbo.group;

/**
 * 当一个服务有多个实现时，可以用goup进行区分
 */
public interface ServerGroup {

    public String operateSystemCommad();
    
    /**
     * 限制并发个数：linux下限制20，windows下限制10 
     */
    public String limitAccess() throws LimitException;
}
