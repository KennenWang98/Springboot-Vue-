package cn.net.sybt.springboot.utils;

public class ResultModel<T> { //返回对象的实体
    private Integer code;
    private String msg;
    private T data;

    public ResultModel() {
    }

    public ResultModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultModel<T> setCode(ResultEnum resultEnum) {
        this.code = resultEnum.code;
        return this;
    }
    public ResultModel<T> setCode(Integer code){
        this.code = code;
        return this;
    }
    public ResultModel<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public ResultModel<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    public T getData() {
        return data;
    }
}
