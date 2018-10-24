package swordToOffer;

/**
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 */
public class Multiply {
    public static void main(String[] args) {

    }

    public int[] multiply(int[] A) {
        if (A == null || A.length <= 1) return A;
        int[] aid = new int[A.length];
        aid[0] = 1;
        for (int i = 1; i < A.length; i++) {
            aid[i] = aid[i - 1] * A[i - 1];
        }
        int right = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            int t = A[i];
            A[i] = aid[i] * right;
            right *= t;
        }
        return A;
    }
}
