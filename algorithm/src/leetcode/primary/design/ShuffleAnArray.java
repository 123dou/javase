package leetcode.primary.design;

import java.util.Random;

public class ShuffleAnArray {
    public static void main(String[] args) {

    }

    int[] oregin;
    int[] curr;

    public ShuffleAnArray(int[] nums) {
        this.curr = nums;
        oregin = curr.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return oregin;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = this.curr.length - 1; i >= 0; i--) {
            swap(curr, i, random.nextInt(i + 1));
        }
        return this.curr;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
