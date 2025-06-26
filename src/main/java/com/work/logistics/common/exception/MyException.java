package com.work.logistics.common.exception;

import com.work.logistics.common.Status;
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
