package christmas.domain;

import static christmas.domain.ChristmasDDayDiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.domain.GiftEvent.GIFT_EVENT;
import static christmas.domain.SpecialDiscountPolicy.SPECIAL_DISCOUNT;
import static christmas.domain.WeekDayDiscountPolicy.WEEK_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountAmountTest {

    @Test
    @DisplayName("주문 메뉴들에 대한 해택 내역을 알려준다.")
    void inform_benefit_details() {
        // given
        LocalDate localDate = LocalDate.of(2023, 12, 3);
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        DiscountAmount discountAmount = new DiscountAmount(createDiscountPolicies(localDate), new GiftEvent());

        // when
        Map<String, Long> result = discountAmount.informBenefitDetails(menus, localDate);

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
}
