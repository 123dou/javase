package testDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Avg> list = new ArrayList<>();
        while (in.hasNextLine()) {
            Avg avg = new Avg();
            String s = in.nextLine();
            if (s.equals("")) break;
            String[] strs = s.trim().split(" ");
            avg.name = strs[0];
            int sum = 0;
            for (int i = 1; i < strs.length; i++) {
                sum += Integer.parseInt(strs[i]);
            }
            avg.avg = (int) Math.round((double) sum / (strs.length - 1));
            list.add(avg);

        }
        Avg[] avgs = new Avg[list.size()];
        for (int i = 0; i < list.size(); i++) {
            avgs[i] = list.get(i);
        }
        sort(avgs);
        for (Avg avg : avgs) {
            System.out.println(avg.name + " " + avg.avg);
        }
    }

    private static void sort(Avg[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1].avg > array[j].avg)
                    swap(array, j, j + 1);
            }
        }
    }

    private static void swap(Avg[] array, int j, int i) {
        Avg t = array[i];
        array[i] = array[j];
        array[j] = t;
    }


    static class Avg {
        String name;
        int avg;
    }
}