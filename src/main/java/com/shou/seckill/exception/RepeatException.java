package com.shou.seckill.exception;

/**
 * 重复秒杀异常
 */
public class RepeatException extends SeckillException{

    public RepeatException(String message) {
        super(message);
    }

    public RepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
