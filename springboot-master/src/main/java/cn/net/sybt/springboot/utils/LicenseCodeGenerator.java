package cn.net.sybt.springboot.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LicenseCodeGenerator {
    private static List<String> code = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
    private static String[] possibleChars = {
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z",
            "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9"
    };

    public static String nextCode() {
        return code.stream().map(t -> {
            int index = (int) Math.floor(Math.random() * possibleChars.length);
            return possibleChars[index];
        }).collect(Collectors.joining());
    }
}
