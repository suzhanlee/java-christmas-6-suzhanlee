package christmas.domain;

import static christmas.domain.Menu.CHAMPAGNE;

import java.util.HashMap;
import java.util.Map;

public class GiftEvent {
    public static final int MIN_ORDER_AMOUNT_FOR_GIFT_EVENT = 12000;
    public static final String GIFT_EVENT = "증정 이벤트";
    public static final int GIFT_NUMBER = 1;

    public boolean supports(long totalOrderAmount) {
        return totalOrderAmount >= MIN_ORDER_AMOUNT_FOR_GIFT_EVENT;
    }

    public Map<Menu, Integer> giveGift() {
        Map<Menu, Integer> gifts = new HashMap<>();
        gifts.put(CHAMPAGNE, GIFT_NUMBER);
        return gifts;
    }

    public Map<String, Long> calculateDiscountAmount() {
        Map<String, Long> benefitDetail = new HashMap<>();
        long discountAmount = CHAMPAGNE.totalMenuPrice(GIFT_NUMBER);
        benefitDetail.put(GIFT_EVENT, discountAmount);
        return benefitDetail;
    }
}
