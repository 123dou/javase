package testDemo;

public class One {
    public static double findMaxAverage(int[] nums, int k) {
        int left = 0, right = 0;
        int sum = 0;
        double max = Integer.MIN_VALUE;
        while (right < k - 1) {
            sum += nums[right];
            right++;
        }
        for (int i = right; i < nums.length; i++) {
            sum += nums[i];
            double m = (double)sum / (double)k;
            max = Math.max(max, m);
            sum -= nums[left++];
        }
        return max;
    }

    //test
    public static void main(String[] args) {
        int[] arr = {1,12,-5,-6,50,3};
        double i = findMaxAverage(arr, 4);
        System.out.println(i);
    }

}
