package com.shou.seckill.exception;

/**
 * 秒杀异常
 */
public class SeckillException extends RuntimeException{
    public SeckillException() {
    }

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(Throwable cause) {
        super(cause);
    }


}
