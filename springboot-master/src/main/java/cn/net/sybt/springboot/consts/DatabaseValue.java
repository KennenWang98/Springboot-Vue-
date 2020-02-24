package cn.net.sybt.springboot.consts;

public interface DatabaseValue {
    Integer IS_TRUE = 1;
    Integer IS_FALSE = 0;
    Integer IS_USED = 1; //许可码已经使用
    Integer NOT_USED = 0;
    String CHOICE = "选择题";
    String SINGLE_CHOICE = "单选题";
    String MULTI_CHOICE = "多选题";
    String FILL = "填空题";
    String MATCH = "连线题";
    String SORT = "排序题";
    String JUDGE = "判断题";
    String ESSAY = "作文题";
}
