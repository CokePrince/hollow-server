package com.hollow.server.utils;

import lombok.Data;

@Data
public class ResponseHelper<T> {
    
    private int code;
    private String prompt;
    private T data;

    public ResponseHelper() {
        this(ResponseCode.SUCCESS, (T) Boolean.valueOf(true));
    }

    public ResponseHelper(T t) {
        if(t == null) {
            this.code = ResponseCode.DATABASE_ERROR.getCode();
            this.prompt = ResponseCode.DATABASE_ERROR.getMsg();
        } else {
            this.code = ResponseCode.SUCCESS.getCode();
            this.prompt = ResponseCode.SUCCESS.getMsg();
        }
        this.data = t;
    }

    public ResponseHelper(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.prompt = responseCode.getMsg();
        this.data = data;
    }

    public ResponseHelper(ResponseCode responseCode, Boolean data, String o) {
        this.code = responseCode.getCode();
        this.prompt = responseCode.getMsg();
        this.data = (T) data;
    }

    public static <T> ResponseHelper<T> error(ResponseCode responseCode) {
        return new ResponseHelper<>(responseCode, false, "");
    }

    public static <T> ResponseHelper<T> error() {
        return ResponseHelper.error(ResponseCode.UNKONWN_ERROR);
    }
}
