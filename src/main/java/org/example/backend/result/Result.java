package org.example.backend.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;


    /**
     * 查询成功
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * 错误
     */
    public static final int ERROR_CODE = 500;
    /**
     * 未登录
     */
    public static final int NO_LOGIN_CODE = 600;
    /**
     * 权限不足
     */

    public static final int NO_AUTH_CODE = 700;

    /**
     * 成功响应的静态方法
     *
     * @param data 传入要返回的数据对象
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(Result.SUCCESS_CODE, "success", data);
    }


    /**
     * 失败响应的静态方法，需手动给出msg和code
     *
     * @param code 手动传入失败码
     * @param msg 传入失败信息
     * @return Result对象
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null);
    }

    /** 失败响应的静态方法，在不提供code时默认使用ERROR_CODE
     * @param msg 传入失败信息
     * @return Result对象
     */
    public static <T> Result<T> error(String msg) {
        return error(ERROR_CODE, msg);
    }
}
