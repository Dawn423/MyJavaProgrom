import java.util.Scanner;

public class BasicCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- 简易计算器 ---");
        System.out.print("请输入第一个数字: ");
        double num1 = sc.nextDouble();

        System.out.print("请输入运算符 (+, -, *, /): ");
        char operator = sc.next().charAt(0);

        System.out.print("请输入第二个数字: ");
        double num2 = sc.nextDouble();

        double result = 0;
        // 标记是否计算成功
        boolean success = true;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                // 强制要求：除数不能为 0
                if (num2 == 0) {
                    System.out.println("错误：除数不能为零！请重新输入：");
                    num2 = sc.nextDouble();
                }
                if (num2 != 0) {
                    result = num1 / num2;
                    break;
                }
            default:
                System.out.println("无效运算符");
                success = false;
        }

        if (success) {
            System.out.println("结果是: " + result);
        }
        sc.close();
    }
}