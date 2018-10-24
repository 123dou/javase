package swordToOffer;

public class IsNumeric {
    public static void main(String[] args) {
        String s = "-1E-16";
        IsNumeric t = new IsNumeric();
        boolean numeric = t.isNumeric(s.toCharArray());
        System.out.println(numeric);
    }

    /**
     * 1.首字母只能为正负号或者数字
     * 2.非数字不能在最后一位
     * 3.第一个非数字只能是'.'或者'e'或者'E'
     * 4.'.'后面一位只能是数字之后的非数字只能是'e'或者'E'
     * 5.'e'或者'E'后面只能是正负号加数字或者数字
     *
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) return false;
        if (!isNumber(str, 0) && !isPosOrNegSign(str, 0)) return false;
        for (int i = 1; i < str.length; i++) {
            if (!isNumber(str, i)) {
                if (str[i] == '.') {
                    if (isOverFlow(str, i + 1)) return false; //小数点不能是最后一位
                    if (!isNumber(str, ++i)) return false; //小数点后一位一定要是一个数字
                    while (!isOverFlow(str, i) && isNumber(str, i)) i++;
                    if (!isOverFlow(str, i)) { //下一个不为数字的符号一定不能在最后一位
                        //.2e2or.2e-/+3.4 就是说小数点之后下一位非数字必须为eOrE
                        if (!isEOre(str, i++)) return false;
                        return afterEOre(str, i);
                    }
                } else if (isEOre(str, i)) {
                    if (isOverFlow(str, i + 1)) return false;
                    return afterEOre(str, i + 1);
                } else return false;
            }
        }
        return true;
    }


    private boolean afterEOre(char[] str, int i) {
        //e之后只能为-/+/0~9
        if (!isPosOrNegSign(str, i) && !isNumber(str, i)) return false;
        //非数字不能是最后一位
        if (isPosOrNegSign(str, i) && isOverFlow(str, i + 1)) return false;
        //之后只能为一个整数
        i++;
        while (!isOverFlow(str, i) && isNumber(str, i)) i++;
        if (!isOverFlow(str, i)) return false;
        return true;
    }

    private boolean isEOre(char[] str, int i) {
        return str[i] == 'e' || str[i] == 'E';
    }

    private boolean isOverFlow(char[] str, int i) {
        return i >= str.length;
    }

    private boolean isPosOrNegSign(char[] str, int i) {
        return str[i] == '-' || str[i] == '+';
    }

    private boolean isNumber(char[] str, int i) {
        return (str[i] >= '0' && str[i] <= '9');
    }


}
