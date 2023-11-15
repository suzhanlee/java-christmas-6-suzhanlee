package christmas.domain;

import christmas.domain.discount.DiscountAmount;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;

public class WooWaRestaurant {

    private final DiscountAmount discountAmount;

    public WooWaRestaurant(DiscountAmount discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Menus findMenusBy(String menuForm) {
        return new Menus(menuForm);
    }

    public OrderedMenuInfo order(LocalDate visitDate, Menus menus) {
        long totalOrderAmount = menus.totalOrderAmount();
        Map<Menu, Integer> gifts = getGifts(totalOrderAmount);
        Map<String, Long> benefitDetails = getBenefits(visitDate, menus);
        long totalDiscountAmount = calculateTotalDiscountAmount(benefitDetails);
        long expectedPaymentAmount = calculateExpectedPaymentAmount(gifts, totalOrderAmount, totalDiscountAmount);
        EventBadge eventBadge = EventBadge.valueOf(totalDiscountAmount);
        return new OrderedMenuInfo(gifts, benefitDetails, totalDiscountAmount, expectedPaymentAmount, eventBadge);
    }

    private Map<Menu, Integer> getGifts(long totalOrderAmount) {
        return discountAmount.checkGift(totalOrderAmount);
    }

    private Map<String, Long> getBenefits(LocalDate visitDate, Menus menus) {
        return discountAmount.informBenefitDetails(menus, visitDate);
    }

    private long calculateTotalDiscountAmount(Map<String, Long> benefitDetails) {
        return benefitDetails.values().stream().mapToLong(discountAmount -> discountAmount).sum();
    }

    private long calculateExpectedPaymentAmount(Map<Menu, Integer> gifts, long totalOrderAmount,
                                                long totalDiscountAmount) {
        long normalPaymentAmount = totalOrderAmount - totalDiscountAmount;
        if (receivedGift(gifts)) {
            return normalPaymentAmount + calculateTotalGiftPrice(gifts);
        }
        return normalPaymentAmount;
    }

    private boolean receivedGift(Map<Menu, Integer> gifts) {
        return !gifts.isEmpty();
    }

    private long calculateTotalGiftPrice(Map<Menu, Integer> gifts) {
        return gifts.entrySet().stream().mapToLong(this::addGiftPrice).sum();
    }

    private long addGiftPrice(Entry<Menu, Integer> gift) {
        Menu menu = gift.getKey();
        Integer quantity = gift.getValue();
        return menu.totalMenuPrice(quantity);
    }
}
