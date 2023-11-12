package christmas.domain;

import java.time.DayOfWeek;

public class WeekendDayDiscountPolicy {

    public boolean supports(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
