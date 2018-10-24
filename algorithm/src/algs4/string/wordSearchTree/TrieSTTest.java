package algs4.string.wordSearchTree;

import org.junit.Before;
import org.junit.Test;

public class TrieSTTest {
    private TrieST<String> trieST;

    @Before
    public void before() {
        trieST = new TrieST<String>();
        put();
    }

    @Test
    public void delete() {
        trieST.delete("she");
    }

    @Test
    public void longestPrefixOf() {
    }

    @Test
    public void keysThatMatch() {
        Iterable<String> keys = trieST.keysThatMatch("s");
        keys.forEach(key -> System.out.println(key));
    }

    @Test
    public void keys() {
        Iterable<String> keys = trieST.keys();
        keys.forEach(key -> System.out.println(key));
    }

    @Test
    public void get() {
        String str = (String) trieST.get("sh");
        System.out.println(str);
    }

    @Test
    public void put() {
        trieST.put("she", "she");
    }
}