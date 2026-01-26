package week1.day2;

import java.util.ArrayList;
import java.util.List;

public class CompanySalarySystem {
    public static void main(String[] args) {
        List<Payable> staffList = new ArrayList<>();

        staffList.add(new Developer("付xx", 3000, 100));
        staffList.add(new Developer("xxx", 3000, 300));

        System.out.println("--- 月度薪资发放列表---");
        for (Payable staff : staffList) {
            System.out.println(staff.getName() + "本月结算金额为：" + staff.calculatePay());
        }
    }
}
