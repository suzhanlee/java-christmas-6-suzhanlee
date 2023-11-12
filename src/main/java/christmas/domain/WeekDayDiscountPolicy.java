package christmas.domain;

import java.time.DayOfWeek;

public class WeekDayDiscountPolicy {
    public boolean supports(DayOfWeek dayOfWeek) {
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }
}
