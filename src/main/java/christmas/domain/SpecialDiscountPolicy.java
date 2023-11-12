package christmas.domain;

public class SpecialDiscountPolicy {
    public boolean supports(int dayOfMonth) {
        return SpecialDay.valueOf(dayOfMonth) != SpecialDay.NORMAL;
    }

    public long calculateDiscountAmount(int totalOrderAmount) {
        return Math.min(totalOrderAmount, 1000);
    }
}
