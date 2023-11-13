package christmas.domain;

import java.util.Map;

public interface DiscountPolicy {
    boolean supports();

    Map<String, Long> calculateDiscountAmount(Menus menus);
}
