package leetcode.primary.math;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
    public static void main(String[] args) {
        int n = 3;
        List<String> list = fizzBuzz(3);
        System.out.println(list);
        System.out.println(1 % 3);
    }

    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) list.add("FizzBuzz");
            else if (i % 3 == 0) list.add("Fizz");
            else if (i % 5 == 0) list.add("Buzz");
            else list.add("" + i);
        }
        return list;
    }
}
