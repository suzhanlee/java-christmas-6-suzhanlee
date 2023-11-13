package christmas.domain.discount.policy;

import christmas.domain.Menus;
import java.util.Map;

public interface DiscountPolicy {
    boolean supports();

    Map<String, Long> calculateDiscountAmount(Menus menus);
}
