package cn.net.sybt.springboot.utils;

public enum ResultEnum {
    SUCCESS(200, "成功"),
    LOGIN_ERROR(222, "登录失败，密码错误！"),
    CODE_EXPIRED(300, "班级的许可码无效或已过期，请联系老师购买新的许可码！"),
    INSERT_ERROR(302, "操作失败，请检查输入参数！"),
    UPDATE_ERROR(304, "更新失败，请检查输入参数！"),
    DELETE_ERROR(310, "删除失败，资源不存在！"),
    UPDATE_TEA_QUES_ERROR(312, "更新失败，该题目不存在！"),
    UPLOAD_TEA_QUES_ERROR(311, "操作失败，未能上传题目！"),
    DELETE_TEA_QUES_ERROR(313, "删除失败，不存在该题目！"),
    GET_HOMEWORK_QUESTION_ERROR(313, "该作业下没有添加过问题！"),
    QUERY_ERROR(320, "查询失败，没有符合条件的信息！"),
    CODE_EXIST(201, "许可码信息无效或已经存在！"),
    ANSWER_EXIST(202, "该学生已经上传过作答！"),
    NOT_FOUND(404, "请求的资源未找到！"),
    NOT_FOUND_HOMEWORK(407, "该班级下没有作业！"),
    NOT_FOUND_TEA_QUES(405, "该老师未上传过题目！"),
    NOT_FOUND_TEA_CLASS_QUES(406, "该老师未在班级中上传过题目！"),
    NOT_FOUND_STUDENT(408, "该班级下没有学生！"),
    NOT_FOUND_PAGES(460, "请求页未找到！"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后重试！");

    public Integer code;
    public String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
