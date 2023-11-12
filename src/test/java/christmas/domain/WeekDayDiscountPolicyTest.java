package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekDayDiscountPolicyTest {

    @ParameterizedTest
    @DisplayName("일요일에서 목요일 사이라면 할인을 적용한다.")
    @CsvSource(value = {"SUNDAY, true", "THURSDAY, true", "FRIDAY, false", "SATURDAY, false"})
    void discount_condition(DayOfWeek dayOfWeek, boolean expected) {
        // given
        WeekDayDiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy();

        // when
        boolean result = weekDayDiscountPolicy.supports(dayOfWeek);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
