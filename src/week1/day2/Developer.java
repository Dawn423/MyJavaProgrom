package week1.day2;

/**
 * 程序员类
 */

public class Developer extends AbstractEmployee {
    /* 加班工资 */
    private final double overtimePay;

    public Developer(String name, double baseSalary, double overtimePay) {
        super(name, baseSalary);
        this.overtimePay = overtimePay;
    }

    /// @return 计算本月总工资金额
    @Override
    public double calculatePay() {
        return baseSalary + overtimePay;
    }

    /// @return 返回人员姓名
    @Override
    public String getName() {
        return this.name;
    }
}
