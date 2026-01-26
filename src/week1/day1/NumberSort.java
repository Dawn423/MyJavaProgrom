package week1.day1;

import java.util.Arrays;
import java.util.Scanner;

public class NumberSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 确定数组长度
        System.out.print("请输入您想排序的数字个数（输完后请按回车键）： ");
        int count = 0;
        if (sc.hasNextInt()) {
            count = sc.nextInt();
        } else {
            System.out.println("错误：请输入一个有效的整数！");
            return;
        }

        // 2. 初始化数组
        int[] nums = new int[count];

        // 3. 循环获取用户输入
        System.out.println("请输入 " + count + " 个整数（每输完一个按回车）：");
        for (int i = 0; i < count; i++) {
            System.out.print("第 " + (i + 1) + " 个数: ");
            while (!sc.hasNextInt()) { // 简单的类型转换防御
                System.out.println("这不是整数，请重新输入：");
                sc.next(); // 消耗掉错误的输入
            }
            nums[i] = sc.nextInt();
        }

        System.out.println("\n排序前: " + Arrays.toString(nums));

        // 4. 冒泡排序逻辑
        bubbleSort(nums);

        System.out.println("排序后: " + Arrays.toString(nums));

        sc.close(); // 养成关闭资源的好习惯
    }

    /**
     * 将排序逻辑封装成方法，提高代码复用性
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 设定一个标志位，如果某一趟没有发生交换，说明已经排好序了
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}