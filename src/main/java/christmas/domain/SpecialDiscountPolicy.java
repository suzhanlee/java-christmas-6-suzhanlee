package christmas.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SpecialDiscountPolicy implements DiscountPolicy {
    public static final String SPECIAL_DISCOUNT = "특별 할인";
    private final LocalDate localDate;

    public SpecialDiscountPolicy(LocalDate localDate) {
        this.localDate = localDate;
    }

    public boolean supports() {
        return SpecialDay.valueOf(localDate.getDayOfMonth()) != SpecialDay.NORMAL;
    }

    public Map<String, Long> calculateDiscountAmount(Menus menus) {
        Map<String, Long> benefitDetail = new HashMap<>();
        long discountAmount = Math.min(menus.totalOrderAmount(), 1000);
        benefitDetail.put(SPECIAL_DISCOUNT, discountAmount);
        return benefitDetail;
    }
}
