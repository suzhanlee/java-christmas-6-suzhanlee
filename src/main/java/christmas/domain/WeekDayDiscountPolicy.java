package christmas.domain;

import java.time.DayOfWeek;

public class WeekDayDiscountPolicy {
    public static final int DISCOUNT_PER_WEEK_DAY_DESSERT = 2023;

    public boolean supports(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public long calculateDiscountAmount(Menus menus) {
        return menus.totalDessertCount() * DISCOUNT_PER_WEEK_DAY_DESSERT;
    }
}
