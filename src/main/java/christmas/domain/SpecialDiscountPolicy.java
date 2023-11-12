package christmas.domain;

public class SpecialDiscountPolicy {
    public boolean supports(int dayOfMonth) {
        return SpecialDay.valueOf(dayOfMonth) != SpecialDay.NORMAL;
    }
}
