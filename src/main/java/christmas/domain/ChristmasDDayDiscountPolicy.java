package christmas.domain;

public class ChristmasDDayDiscountPolicy {
    public boolean supports(int day) {
        return day >= 1 && day <= 25;
    }
}
