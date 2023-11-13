package christmas.domain;

import java.util.Map;

public record OrderedMenuInfo(Map<Menu, Integer> gifts, Map<String, Long> benefitDetails, long totalDiscountAmount,
                              long expectedPaymentAmount, EventBadge eventBadge) {
}
