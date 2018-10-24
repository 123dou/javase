package chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * final变量没有在没有初化之前输出
 */
public class FinalContructorTest {
    static abstract class A{
        public A() {
            diplay();
        }

        protected abstract void diplay();
    }

    static class B extends A{
        private int INT = 100;
        private final int FINALE_INT = 100;
        private final Integer FINALE_INTEGER = 100;
        private final String STR1 = "abc";
        private final String FINALE_STR2 = new String("abc");
        private final List<String> FINALE_LIST = new ArrayList<>();
        public B(){
            super();
            System.out.println("abc");
        }
        @Override
        protected void diplay() {
            System.out.println("INT--" + INT);
            System.out.println("FINALE_INT--" + FINALE_INT);
            System.out.println("FIANLE_INTEGER--" + FINALE_INTEGER);
            System.out.println("STR1--" + STR1);
            System.out.println("FINALE_STR2--" + FINALE_STR2);
            System.out.println("FINALE_LIST--" + FINALE_LIST);
        }
    }

    public static void main(String[] args) {
        new B();
    }
}
