package com.suprised.dubbo.group;

public class LimitException extends RuntimeException {

    private static final long serialVersionUID = 9220784599568181563L;

    public LimitException(String msg) {
        super(msg);
    }
}
