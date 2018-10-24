package algs4.string.regex;

public class Test {
    public static void main(String[] args) {
        NFA nfa = new NFA("ABACD");
        System.out.println(nfa.recognizes("ABACD"));

    }
}
