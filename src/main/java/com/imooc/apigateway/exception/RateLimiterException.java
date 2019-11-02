package com.imooc.apigateway.exception;

/**
 * 限流的异常类，一旦超过最大的上限，则抛出异常
 */
public class RateLimiterException extends RuntimeException{


}
