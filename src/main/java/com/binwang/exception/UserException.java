package com.binwang.exception;

/**
 * Created by owen on 17/5/11.
 */
public class UserException extends RuntimeException {
    public UserException() {
    }

    public UserException(String msg) {
        super(msg);
    }
}
