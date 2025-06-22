package com.zhtang.miaosha.common.exception;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;

import com.zhtang.miaosha.common.Status;
import lombok.Getter;

@Getter
public class MyException extends Exception {
    private final Integer code;
    private final String message;

    public MyException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MyException(String message) {
        this(Status.ERROR.getCode(), message);
    }

    public MyException(Status status)  {
        this(status.getCode(), status.getMessage());
    }

    public MyException() {
        this(Status.ERROR);
    }

}
