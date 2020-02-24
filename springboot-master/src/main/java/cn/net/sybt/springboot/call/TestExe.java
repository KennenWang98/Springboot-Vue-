package cn.net.sybt.springboot.call;

import java.io.IOException;

public class TestExe {
    public static void useExe() {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        try {
            //process = runtime.exec("D:\\计蒜客实训\\项目文件\\NKHandwrittenEval\\NKHandwrittenEval\\NKHandwrittenEval.exe");
            //p = rt.exec("E:\\WorkSoftware\\VS Code\\Microsoft VS Code\\Microsoft VS Code\\Code.exe");
            p = rt.exec("cmd /c ipconfig");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        useExe();
    }
}
