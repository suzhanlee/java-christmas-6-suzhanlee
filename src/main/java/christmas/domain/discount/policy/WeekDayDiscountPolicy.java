package christmas.domain.discount.policy;

import christmas.domain.Menus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    public static final int DISCOUNT_PER_WEEK_DAY_DESSERT = 2023;
    public static final String WEEK_DAY_DISCOUNT = "평일 할인";
    private final LocalDate visitDate;

    public WeekDayDiscountPolicy(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public boolean supports() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public Map<String, Long> calculateDiscountAmount(Menus menus) {
        Map<String, Long> benefitDetail = new HashMap<>();
        long discountAmount = menus.totalDessertCount() * DISCOUNT_PER_WEEK_DAY_DESSERT;
        benefitDetail.put(WEEK_DAY_DISCOUNT, discountAmount);
        return benefitDetail;
    }
}
