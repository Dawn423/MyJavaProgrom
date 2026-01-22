package day3.datadeduplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据去重
 */

public class DeduplicationData {
    public static void main(String[] args) {
        // 模拟重复数据列表
        List<String> repeatData = new ArrayList<>();
        repeatData.add("A");
        repeatData.add("B");
        repeatData.add("C");
        repeatData.add("D");
        repeatData.add("A");  // 重复数据

        System.out.println("去重前的数据：" + repeatData);

        // 使用Set构造方法进行数据去重
        Set<String> updateSet = new HashSet<>(repeatData);

        System.out.println("去重后的数据：" + updateSet);
    }
}
