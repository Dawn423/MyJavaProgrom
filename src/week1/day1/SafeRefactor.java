package week1.day1;

import java.util.Scanner;

public class SafeRefactor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String input = scanner.nextLine();

        // 1. 预防空指针 (NullPointerException)
        if (input != null) {
            System.out.println("长度为: " + input.length());
        } else {
            System.out.println("警告：输入字符串为空，已跳过处理。");
        }

        // 2. 类型转换
        System.out.println("请输入原始价格: ");
        double price = 0.0;
        boolean flag = false;
        while (!flag) {
            String priceInput = scanner.nextLine().trim();
            try {
                price = Double.parseDouble(priceInput);
                flag = true;
                int roundedPrice = (int) price; // 强制转换，舍去小数
                System.out.println("原始价格: " + price + ", 整数价格: " + roundedPrice);
            } catch (NumberFormatException e) {
                System.out.println("输入错误，请输入有效的数字格式：");
            }
        }


        // 3. 字符串转数字
        int val = 0;
        boolean flag2 = false;
        System.out.println("请输入要转换成数字的字符串： ");
        while (!flag2) {
            String strNum = scanner.nextLine().trim();
            try {
                val = Integer.parseInt(strNum);
                flag2 = true;
                System.out.println("转换成功: " + val);
            } catch (NumberFormatException e) {
                System.out.println("转换失败，请输入正确的数字格式:");
            }
        }
        scanner.close();
    }
}