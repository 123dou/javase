package leetcode.traceback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
 * <p>
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 * <p>
 * <p>
 * <p>
 * 例如，上面的二进制手表读取 “3:25”。
 * <p>
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 * <p>
 * 案例:
 * <p>
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * <p>
 * <p>
 * 注意事项:
 * <p>
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 */
public class BinaryWatch {

    Map<Integer, List<String>> mapH = new HashMap<>();
    Map<Integer, List<String>> mapM = new HashMap<>();

    {
        for (int i = 0; i < 12; i++) {
            int c = Integer.bitCount(i);
            if (mapH.containsKey(c)) {
                mapH.get(c).add("" + i);
            } else {
                List<String> list = new ArrayList<>();
                list.add("" + i);
                mapH.put(c, list);
            }
        }
        for (int i = 0; i < 60; i++) {
            int c = Integer.bitCount(i);
            StringBuilder sb = new StringBuilder();
            sb.append(":");
            if (i < 10) sb.append(0);
            sb.append(i);
            if (mapM.containsKey(c)) {
                mapM.get(c).add(sb.toString());
            } else {
                List<String> list = new ArrayList<>();
                list.add(sb.toString());
                mapM.put(c, list);
            }
        }
    }

    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        if (num > 8) return list;
        for (int i = 0; i <= 3; i++) {
            int n = num - i;
            if (n >= 0 && n <= 5) readBinaryWatch(i, n, list);
        }
        return list;
    }

    private void readBinaryWatch(int h, int m, List<String> list) {
        List<String> listH = mapH.get(h);
        List<String> listM = mapM.get(m);
        for (int i = 0; i < listH.size(); i++) {
            String hour = listH.get(i);
            for (int j = 0; j < listM.size(); j++) {
                String min = listM.get(j);
                list.add(hour + min);
            }
        }
    }
}
