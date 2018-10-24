package chapter2;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
//        String str1 = new StringBuilder("计算机").append("软件").toString();
//        System.out.println(str1.intern() == str1);
//        String str2 = new StringBuilder("jaa").append("va").toString();
//        System.out.println(str2.intern() == str2);
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
