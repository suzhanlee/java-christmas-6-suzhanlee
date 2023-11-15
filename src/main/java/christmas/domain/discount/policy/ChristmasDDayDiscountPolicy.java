package christmas.domain.discount.policy;

import christmas.domain.Menus;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ChristmasDDayDiscountPolicy implements DiscountPolicy {

    public static final int DEFAULT_CHRISTMAS_D_DAT_DISCOUNT_AMOUNT = 1000;
    public static final int TWENTY_FIVE_DAY = 25;
    public static final long CHRISTMAS_INCREMENT = 100L;
    public static final String CHRISTMAS_D_DAY_DISCOUNT = "크리스마스 디데이 할인";
    private final LocalDate visitDate;

    public ChristmasDDayDiscountPolicy(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public boolean supports() {
        return visitDate.getDayOfMonth() <= TWENTY_FIVE_DAY;
    }

    public Map<String, Long> calculateDiscountAmount(Menus menus) {
        Map<String, Long> benefitDetail = new HashMap<>();
        long discountAmount =
                DEFAULT_CHRISTMAS_D_DAT_DISCOUNT_AMOUNT + (visitDate.getDayOfMonth() - 1) * CHRISTMAS_INCREMENT;
        benefitDetail.put(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
        return benefitDetail;
    }
}
