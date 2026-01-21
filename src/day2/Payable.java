package day2;

/**
 * 薪资结算接口
 * * @author 实习生：付xx、xxx
 * * @date 2026/1/21
 */

public interface Payable {
    /**
     * 获取人员姓名，计算月结薪资
     * * @return 薪资总额
     */
    String getName();
    double calculatePay();
}
