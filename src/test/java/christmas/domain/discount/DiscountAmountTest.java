package christmas.domain.discount;

import static christmas.domain.discount.GiftEvent.GIFT_EVENT;
import static christmas.domain.discount.policy.ChristmasDDayDiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.domain.discount.policy.SpecialDiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.domain.discount.policy.WeekDayDiscountPolicy.WEEK_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menu;
import christmas.domain.Menus;
import christmas.domain.discount.policy.ChristmasDDayDiscountPolicy;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekDayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiscountAmountTest {

    @Test
    @DisplayName("주문 메뉴들에 대한 해택 내역을 알려준다.")
    void inform_benefit_details() {
        // given
        LocalDate visitDate = LocalDate.of(2023, 12, 3);
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        DiscountAmount discountAmount = new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent());

        // when
        Map<String, Long> result = discountAmount.informBenefitDetails(menus, visitDate);

        // then
        assertThat(result).isEqualTo(createExpectedBenefitDetails());
    }

    private List<DiscountPolicy> createDiscountPolicies(LocalDate localDate) {
        List<DiscountPolicy> discountPolicies = new ArrayList<>();
        discountPolicies.add(new ChristmasDDayDiscountPolicy(localDate));
        discountPolicies.add(new WeekDayDiscountPolicy(localDate));
        discountPolicies.add(new WeekendDiscountPolicy(localDate));
        discountPolicies.add(new SpecialDiscountPolicy(localDate));
        return discountPolicies;
    }

    private Map<String, Long> createExpectedBenefitDetails() {
        Map<String, Long> benefitDetails = new HashMap<>();
        benefitDetails.put(CHRISTMAS_D_DAY_DISCOUNT, 1200L);
        benefitDetails.put(WEEK_DAY_DISCOUNT, 4046L);
        benefitDetails.put(SPECIAL_DISCOUNT, 1000L);
        benefitDetails.put(GIFT_EVENT, 25000L);
        return benefitDetails;
    }

    @Test
    @DisplayName("증정 상품이 있으면 증정 상품과 개수를 알려준다.")
    void inform_if_there_is_gift() {
        // given
        LocalDate visitDate = LocalDate.of(2023, 12, 3);
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        DiscountAmount discountAmount = new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent());

        // when
        Map<Menu, Integer> result = discountAmount.checkGift(menus.totalOrderAmount());

        // then
        assertThat(result).isEqualTo(createExpectedGift());
    }

    private Map<Menu, Integer> createExpectedGift() {
        Map<Menu, Integer> gifts = new HashMap<>();
        gifts.put(Menu.CHAMPAGNE, 1);
        return gifts;
    }

    @ParameterizedTest
    @DisplayName("2023년 12월 1일 에서 31일 사이가 아니라면 할인 이벤트를 적용하지 않는다.")
    @CsvSource(value = {"23, 11, 30", "24, 1, 1"})
    void no_discount_if_it_is_not_within_period(int year, int month, int dayOfMonth) {
        // given
        LocalDate visitDate = LocalDate.of(year, month, dayOfMonth);
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        DiscountAmount discountAmount = new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent());

        // when
        Map<String, Long> result = discountAmount.informBenefitDetails(menus, visitDate);

        // then
        assertThat(result).isEqualTo(createNoBenefitDetails());
    }

    private Map<String, Long> createNoBenefitDetails() {
        return Collections.emptyMap();
    }

    @Test
    @DisplayName("할인 전 총 주문 금액이 10000이 넘지 않으면 할인을 하지 않는다.")
    void no_discount_if_total_order_amount_does_not_exceed_certain_amount() {
        // given
        LocalDate visitDate = LocalDate.of(2023, 12, 23);
        Menus menus = new Menus("아이스크림-1");
        DiscountAmount discountAmount = new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent());

        // when
        Map<String, Long> result = discountAmount.informBenefitDetails(menus, visitDate);

        // then
        assertThat(result).isEqualTo(createNoBenefitDetails());
    }
}
