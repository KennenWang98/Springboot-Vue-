package cn.net.sybt.springboot.call;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestCMD {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        try {
            Process p = rt.exec("ipconfig");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s = br.readLine();
            while (null != s) {
                System.out.println(s);
                s = br.readLine();
            }
            br.close();

            //导致当前线程等待，如果必要，一直要等到由该 Process 对象表示的进程已经终止。
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
