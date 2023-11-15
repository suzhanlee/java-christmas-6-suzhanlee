package christmas.domain.discount;

import static java.time.Month.DECEMBER;

import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.discount.policy.DiscountPolicy;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountAmount {
    public static final int MIN_ORDER_AMOUNT_FOR_DISCOUNT = 10000;
    public static final int DISCOUNT_YEAR = 2023;
    private final List<DiscountPolicy> discountPolicies;
    private final GiftEvent giftEvent;

    public DiscountAmount(List<DiscountPolicy> discountPolicies, GiftEvent giftEvent) {
        this.discountPolicies = discountPolicies;
        this.giftEvent = giftEvent;
    }

    public Map<String, Long> informBenefitDetails(Menus menus, LocalDate visitDate) {
        Map<String, Long> benefitDetails = new HashMap<>();
        if (!discountable(menus, visitDate)) {
            return benefitDetails;
        }
        return discountedBenefitDetails(menus, benefitDetails);
    }

    private boolean discountable(Menus menus, LocalDate visitDate) {
        return menus.totalOrderAmount() >= MIN_ORDER_AMOUNT_FOR_DISCOUNT && visitDate.getYear() == DISCOUNT_YEAR
                && visitDate.getMonth() == DECEMBER;
    }

    private Map<String, Long> discountedBenefitDetails(Menus menus, Map<String, Long> benefitDetails) {
        for (DiscountPolicy discountPolicy : discountPolicies) {
            addBenefitDetail(menus, benefitDetails, discountPolicy);
        }
        addGiftBenefitDetail(menus, benefitDetails);
        return benefitDetails;
    }

    private void addBenefitDetail(Menus menus, Map<String, Long> benefitDetails, DiscountPolicy discountPolicy) {
        if (discountPolicy.supports()) {
            benefitDetails.putAll(discountPolicy.calculateDiscountAmount(menus));
        }
    }

    private void addGiftBenefitDetail(Menus menus, Map<String, Long> benefitDetails) {
        if (giftEvent.supports(menus.totalOrderAmount())) {
            benefitDetails.putAll(giftEvent.calculateDiscountAmount());
        }
    }

    public Map<Menu, Integer> checkGift(long totalOrderAmount) {
        if (giftEvent.supports(totalOrderAmount)) {
            return giftEvent.giveGift();
        }
        return new HashMap<>();
    }
}
