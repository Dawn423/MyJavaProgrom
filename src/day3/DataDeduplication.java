package day3;

// 引入 HashSet 类
import java.util.HashSet;

public class DataDeduplication {
    public static void main(String[] args) {
        HashSet<String> list = new HashSet<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("A");  // 重复的元素不会被添加
        System.out.println(list);
    }
}