package christmas.domain;

import static christmas.domain.EventBadge.SANTA;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.discount.GiftEvent.GIFT_EVENT;
import static christmas.domain.discount.policy.ChristmasDDayDiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.domain.discount.policy.SpecialDiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.domain.discount.policy.WeekDayDiscountPolicy.WEEK_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.discount.DiscountAmount;
import christmas.domain.discount.GiftEvent;
import christmas.domain.discount.policy.ChristmasDDayDiscountPolicy;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekDayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WooWaRestaurantTest {

    @Test
    @DisplayName("음식들을 주문해 받을 혜택들을 미리 볼 수 있다.")
    void preview_the_benefits_by_ordering() {
        // given
        LocalDate visitDate = createVisitDate();
        WooWaRestaurant wooWaRestaurant = new WooWaRestaurant(createDiscountAmount(visitDate));

        // when
        OrderedMenuInfo result = wooWaRestaurant.order(visitDate, createMenus());

        // then
        assertThat(result).isEqualTo(createExpectedOrderedMenuInfo());
    }

    private LocalDate createVisitDate() {
        return LocalDate.of(2023, 12, 25);
    }

    private DiscountAmount createDiscountAmount(LocalDate visitDate) {
        return new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent());
    }

    private List<DiscountPolicy> createDiscountPolicies(LocalDate visitDate) {
        List<DiscountPolicy> discountPolicies = new ArrayList<>();
        discountPolicies.add(new ChristmasDDayDiscountPolicy(visitDate));
        discountPolicies.add(new WeekDayDiscountPolicy(visitDate));
        discountPolicies.add(new WeekendDiscountPolicy(visitDate));
        discountPolicies.add(new SpecialDiscountPolicy(visitDate));
        return discountPolicies;
    }

    private Menus createMenus() {
        return new Menus("해산물파스타-1,크리스마스파스타-5,초코케이크-2,아이스크림-4,제로콜라-1,레드와인-2");
    }

    private OrderedMenuInfo createExpectedOrderedMenuInfo() {
        return new OrderedMenuInfo(createGifts(), createBenefitDetails(), 41538L, 316462L, SANTA);

    }

    private Map<Menu, Integer> createGifts() {
        Map<Menu, Integer> gifts = new HashMap<>();
        gifts.put(CHAMPAGNE, 1);
        return gifts;
    }

    private Map<String, Long> createBenefitDetails() {
        Map<String, Long> benefitDetails = new LinkedHashMap<>();
        benefitDetails.put(CHRISTMAS_D_DAY_DISCOUNT, 3400L);
        benefitDetails.put(WEEK_DAY_DISCOUNT, 12138L);
        benefitDetails.put(SPECIAL_DISCOUNT, 1000L);
        benefitDetails.put(GIFT_EVENT, 25000L);
        return benefitDetails;
    }
}