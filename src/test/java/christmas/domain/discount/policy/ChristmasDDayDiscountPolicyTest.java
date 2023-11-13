package christmas.domain.discount.policy;

import static christmas.domain.discount.policy.ChristmasDDayDiscountPolicy.CHRISTMAS_D_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menus;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChristmasDDayDiscountPolicyTest {

    @ParameterizedTest
    @DisplayName("12월 1일과 25일 사이라면 할인을 적용한다.")
    @CsvSource(value = {"1, true", "25, true", "26, false", "31, false"})
    void discount_condition(int day, boolean expected) {
        // given
        LocalDate localDate = LocalDate.of(2023, 12, day);
        ChristmasDDayDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy(localDate);

        // when
        boolean discountable = christmasDDayDiscountPolicy.supports();

        // then
        assertThat(discountable).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("할인 금액은 1000원으로 시작해 크리스마스가 다가올 수록 1000원씩 증가시킨다.")
    @CsvSource(value = {"1, 1000", "2, 1100", "25, 3400"})
    void calculate_discount_amount(int day, long discountAmount) {
        // given
        LocalDate localDate = LocalDate.of(2023, 12, day);
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        ChristmasDDayDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy(localDate);

        // when
        Map<String, Long> result = christmasDDayDiscountPolicy.calculateDiscountAmount(menus);

        // then
        assertThat(result).isEqualTo(createExpected(discountAmount));
    }

    private Map<String, Long> createExpected(long discountAmount) {
        Map<String, Long> expected = new HashMap<>();
        expected.put(CHRISTMAS_D_DAY_DISCOUNT, discountAmount);
        return expected;
    }
}
