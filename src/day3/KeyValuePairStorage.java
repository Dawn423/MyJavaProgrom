package day3;

/// 键值对存储

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KeyValuePairStorage {
    public static void main(String[] args) {
        Map<Integer, String> studentMap = new HashMap<>(3);

        // 创建学生学号地图，便于快速查找
        studentMap.put(202601, "John");
        studentMap.put(202602, "Mary");
        studentMap.put(202603, "Jane");

        // 快速查找
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("请输入想要查找的学生ID: ");

                try {
                    Integer targetId = scanner.nextInt();
                    String studentName = studentMap.get(targetId);

                    if (studentName != null) {
                        // 输入正确的学生学号
                        System.out.println("学号" + targetId + "对应的学生是：" + studentMap.get(targetId));
                    } else {
                        // 输入不存在的学生学号
                        System.out.println("学号" + targetId + "对应的学生不存在");
                    }
                    break;
                } catch (Exception e) {
                    // 输入格式不正确
                    scanner.next();
                    System.out.println("输入无效，请输入正确的数字学号");
                }
            }
        }
    }
}
