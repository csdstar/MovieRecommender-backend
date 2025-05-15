package org.example.backend.exception;

/**
 * 自定义异常类，用于服务层异常处理。
 */
public class ServiceException extends RuntimeException {

    /**
     * 构造器，可以传递错误消息。
     *
     * @param message 错误消息
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 构造器，可以传递错误消息和引起异常的原因。
     *
     * @param message 错误消息
     * @param cause   引起异常的原因
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

