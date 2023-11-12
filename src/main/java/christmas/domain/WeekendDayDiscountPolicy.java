package christmas.domain;

import java.time.DayOfWeek;

public class WeekendDayDiscountPolicy {

    public static final int DISCOUNT_PER_WEEKEND_MAIN_MENU = 2023;

    public boolean supports(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public long calculateDiscountAmount(Menus menus) {
        return menus.totalMainCount() * DISCOUNT_PER_WEEKEND_MAIN_MENU;
    }
}
