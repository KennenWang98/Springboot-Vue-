package cn.net.sybt.springboot.utils;

public class RetResponse { //将结果的数据格式进行封装，返回封装后的数据对象
    public static <T> ResultModel<T> okResp() { //成功返回
        return new ResultModel<>(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.msg);
    }

    public static <T> ResultModel<T> okResp(T data) { //成功返回
        return new ResultModel<>(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.msg, data);
    }

    public static <T> ResultModel<T> resp(int code, String msg) { //通用的返回
        return new ResultModel<>(code, msg);
    }

    public static <T> ResultModel<T> resp(int code, String msg, T data) { //通用的返回
        return new ResultModel<>(code, msg, data);
    }
}
