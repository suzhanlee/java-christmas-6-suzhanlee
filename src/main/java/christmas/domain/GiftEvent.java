package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class GiftEvent {
    public static final int MIN_ORDER_AMOUNT_FOR_GIFT_EVENT = 12000;

    public boolean supports(long totalOrderAmount) {
        return totalOrderAmount >= MIN_ORDER_AMOUNT_FOR_GIFT_EVENT;
    }

    public Map<Menu, Integer> giveGift() {
        Map<Menu, Integer> gifts = new HashMap<>();
        gifts.put(Menu.CHAMPAGNE, 1);
        return gifts;
    }
}
