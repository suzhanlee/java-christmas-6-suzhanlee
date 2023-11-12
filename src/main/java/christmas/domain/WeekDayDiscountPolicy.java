package christmas.domain;

import java.time.DayOfWeek;

public class WeekDayDiscountPolicy {
    public static final int WEEK_DAY_DESSERT_PER_DISCOUNT_AMOUNT = 2023;

    public boolean supports(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public long calculateDiscountAmount(Menus menus) {
        return menus.totalDessertCount() * WEEK_DAY_DESSERT_PER_DISCOUNT_AMOUNT;
    }
}
