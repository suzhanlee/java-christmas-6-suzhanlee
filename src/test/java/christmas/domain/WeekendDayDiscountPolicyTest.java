package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekendDayDiscountPolicyTest {

    @ParameterizedTest
    @DisplayName("금요일이나 토요일이라면 할인을 적용한다")
    @CsvSource(value = {"SUNDAY, false", "THURSDAY, false", "FRIDAY, true", "SATURDAY, true"})
    void discount_condition(DayOfWeek dayOfWeek, boolean expected) {
        // given
        WeekendDayDiscountPolicy weekendDayDiscountPolicy = new WeekendDayDiscountPolicy();

        // when
        boolean result = weekendDayDiscountPolicy.supports(dayOfWeek);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
