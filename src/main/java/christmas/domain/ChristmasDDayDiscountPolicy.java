package christmas.domain;

public class ChristmasDDayDiscountPolicy {

    public static final int DEFAULT_CHRISTMAS_D_DAT_DISCOUNT_AMOUNT = 1000;
    public static final int ONE_DAY = 1;
    public static final int TWENTY_FIVE_DAY = 25;
    public static final long CHRISTMAS_INCREMENT = 100L;

    public boolean supports(int day) {
        return day >= ONE_DAY && day <= TWENTY_FIVE_DAY;
    }

    public long calculateDiscountAmount(int day) {
        return DEFAULT_CHRISTMAS_D_DAT_DISCOUNT_AMOUNT + (day - 1) * CHRISTMAS_INCREMENT;
    }
}
