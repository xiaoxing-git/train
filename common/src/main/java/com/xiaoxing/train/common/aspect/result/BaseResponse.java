package com.xiaoxing.train.common.aspect.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 通用返回类
 */
@Data
public class BaseResponse<T> implements Serializable {

    /**
     * 状态码
     */
    private int code;

    /**
     * 数据
     */
    private T data;

    /**
     * 消息
     */
    private String message;

    /**
     * 描述
     */
    private String description;

    /**
     * 异常描述列表
     */
    private List descriptionList;

    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String message, List<Exception> descriptionList) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.descriptionList = descriptionList;
    }

    public BaseResponse(int code, T data, String message) {
        this(code, data, message, "");
    }

    public BaseResponse(int code, T data) {
        this(code, data, "", "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }
}
