package christmas.domain.discount.policy;

import christmas.domain.Menus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WeekendDiscountPolicy implements DiscountPolicy {

    public static final int DISCOUNT_PER_WEEKEND_MAIN_MENU = 2023;
    public static final String WEEKEND_DISCOUNT = "주말 할인";
    private final LocalDate visitDate;

    public WeekendDiscountPolicy(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public boolean supports() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public Map<String, Long> calculateDiscountAmount(Menus menus) {
        Map<String, Long> benefitDetail = new HashMap<>();
        long discountAmount = menus.totalMainCount() * DISCOUNT_PER_WEEKEND_MAIN_MENU;
        benefitDetail.put(WEEKEND_DISCOUNT, discountAmount);
        return benefitDetail;
    }
}
