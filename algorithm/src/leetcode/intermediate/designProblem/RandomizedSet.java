package leetcode.intermediate.designProblem;

import java.util.*;

public class RandomizedSet {
    public static void main(String[] args) {
        RandomizedSet rz = new RandomizedSet();
        System.out.println(rz.insert(1));
        System.out.println(rz.remove(2));
        System.out.println(rz.insert(2));
        System.out.println(rz.getRandom());
        System.out.println(rz.remove(1));
        System.out.println(rz.insert(2));
        System.out.println(rz.getRandom());
    }

    Map<Integer, Integer> map;
    List<Integer> list;
    int pos;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, map.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            int pos = map.get(val);
            if (pos != list.size() - 1) {
                int lastIndexValue = list.get(list.size() - 1);
                list.set(pos, lastIndexValue);
                map.put(lastIndexValue, pos);
            }
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int i = new Random().nextInt(list.size());
        return list.get(i);
    }
}
