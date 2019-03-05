package com.pp.base.common;

import com.github.pagehelper.PageInfo;

/**
 * 返回的参数封装类
 */
public class RJResponse<T> {

    private static final boolean CODE_SUCCESS = true;

    private static final boolean CODE_FAIL = false;

    private boolean code;
    private boolean success;
    private T data;
    private String msg;
    private String message;
    private String status;
    private Long total;
    private Integer pageSize;


    public RJResponse(){

    }

    public RJResponse(boolean code){
        this.code = code;
        this.success = code;
        if(code){
            this.status = "200";
        }
    }

    public RJResponse(boolean code, T data){
        this.code = code;
        this.success = code;
        this.data = data;
        if(code){
            this.status = "200";
        }
    }

    public RJResponse(boolean code, String msg){
        this.code = code;
        this.success = code;
        this.msg = msg;
        this.message = msg;
        if(code){
            this.status = "200";
        }
    }

    public static RJResponse success(){
        return new RJResponse(CODE_SUCCESS);
    }

    public static RJResponse success(Object data){
        return new RJResponse(CODE_SUCCESS, data);
    }

    public static RJResponse page(PageInfo data){
        RJResponse rjResponse = new RJResponse(CODE_SUCCESS, data.getList());
        rjResponse.setTotal(data.getTotal());
        rjResponse.setPageSize(data.getPageSize());
        return rjResponse;
    };

    public static RJResponse success(String msg){
        return new RJResponse(CODE_SUCCESS, msg);
    }

    public static RJResponse success(String msg, Object data){
        RJResponse rj = new RJResponse(CODE_SUCCESS, msg);
        rj.setData(data);
        return rj;
    }

    public static RJResponse fail(){
        return new RJResponse(CODE_FAIL);
    }

    public static RJResponse fail(String msg){
        return new RJResponse(CODE_FAIL, msg);
    }

    public static RJResponse widthCode(boolean errorCode) {
        return new RJResponse(errorCode);
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
