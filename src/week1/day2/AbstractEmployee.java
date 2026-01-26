package week1.day2;

/**
 * 员工抽象类
 */

public abstract class AbstractEmployee implements Payable {
    /* 姓名 */
    protected String name;
    /* 基本工资 */
    protected double baseSalary;

    public AbstractEmployee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }
}
