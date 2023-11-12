package christmas.domain;

public class GiftEvent {
    public static final int MIN_ORDER_AMOUNT_FOR_GIFT_EVENT = 12000;

    public boolean supports(long totalOrderAmount) {
        return totalOrderAmount >= MIN_ORDER_AMOUNT_FOR_GIFT_EVENT;
    }
}
