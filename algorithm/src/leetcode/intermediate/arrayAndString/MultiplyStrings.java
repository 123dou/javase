package leetcode.intermediate.arrayAndString;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        MultiplyStrings m = new MultiplyStrings();
        String a = "880980880980980808098080808080980980809809800773453";
        String b = "4524526565635653655635425432";
        String s = m.multiply(a, b);
        System.out.println(s);
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.equals("0") || num2.equals("0")) return "0";
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int[] mul = new int[c1.length + c2.length];
        if (c2.length > c1.length) {
            char[] t = c1;
            c1 = c2;
            c2 = t;
        }


        for (int j = c2.length - 1, offset = 0; j >= 0; j--) {
            int[] arr = mul(c1, c2[j] - '0', offset++);
            add(mul, arr);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = mul.length - 1; i >= 0; i--) {
            if (mul[i] != 0) {
                for (; i >= 0; i--) sb.append(mul[i]);
            }
        }
        return sb.toString();
    }

    private int[] mul(char[] arr, int n, int offset) {
        int[] res = new int[arr.length + offset + 1];
        int count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int mul = (arr[i] - '0') * n + count;
            if (mul > 9) {
                count = mul / 10;
                mul %= 10;
            } else count = 0;
            res[offset++] = mul;
        }
        if (count > 0) res[res.length - 1] = count;
        return res;
    }


    private void add(int[] mul, int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = mul[i] + arr[i] + count;
            if (sum > 9) {
                count = 1;
                sum -= 10;
            } else count = 0;
            mul[i] = sum;
        }
        if (count == 1) {
            if (arr.length < mul.length) mul[arr.length] = count;
            else mul[arr.length - 1] = count;
        }
    }
}
