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
}
