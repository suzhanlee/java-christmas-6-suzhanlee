package christmas.domain.discount.policy;

import christmas.domain.Menus;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SpecialDiscountPolicy implements DiscountPolicy {
    public static final String SPECIAL_DISCOUNT = "특별 할인";
    private final LocalDate visitDate;

    public SpecialDiscountPolicy(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public boolean supports() {
        return SpecialDay.valueOf(visitDate.getDayOfMonth()) != SpecialDay.NORMAL;
    }

    public Map<String, Long> calculateDiscountAmount(Menus menus) {
        Map<String, Long> benefitDetail = new HashMap<>();
        long discountAmount = Math.min(menus.totalOrderAmount(), 1000);
        benefitDetail.put(SPECIAL_DISCOUNT, discountAmount);
        return benefitDetail;
    }
}
