package cn.net.sybt.springboot.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
    //返回一个拼音字符串，并且首字母大写
    public static String getPingYin(String src) {
        char[] charArray = src.toCharArray();
        String[] strings;
        HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();
        pinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder stringBuilder = new StringBuilder();
        int t0 = charArray.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(charArray[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    strings = PinyinHelper.toHanyuPinyinStringArray(charArray[i], pinyinOutputFormat);
                    stringBuilder.append(strings[0].substring(0, 1).toUpperCase()).append(strings[0].substring(1));
                } else {
                    stringBuilder.append(charArray[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
