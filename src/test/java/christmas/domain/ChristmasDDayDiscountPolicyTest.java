package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChristmasDDayDiscountPolicyTest {

    @ParameterizedTest
    @DisplayName("12월 1일과 25일 사이라면 할인을 적용한다.")
    @CsvSource(value = {"1, true", "25, true", "26, false", "31, false"})
    void discountable(int day, boolean expected) {
        // given
        ChristmasDDayDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy();

        // when
        boolean discountable = christmasDDayDiscountPolicy.supports(day);

        // then
        assertThat(discountable).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("할인 금액은 1000원으로 시작해 크리스마스가 다가올 수록 1000원씩 증가시킨다.")
    @CsvSource(value = {"1, 1000", "2, 1100", "25, 3400"})
    void discountAmount(int day, long expected) {
        // given
        ChristmasDDayDiscountPolicy christmasDDayDiscountPolicy = new ChristmasDDayDiscountPolicy();

        // when
        long discountAmount = christmasDDayDiscountPolicy.calculateDiscountAmount(day);

        // then
        assertThat(discountAmount).isEqualTo(expected);
    }
}
