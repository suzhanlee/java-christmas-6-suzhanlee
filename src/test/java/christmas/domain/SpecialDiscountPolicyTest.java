package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpecialDiscountPolicyTest {

    @ParameterizedTest
    @DisplayName("이벤트 달력에 별이 있으면 할인을 적용한다.")
    @CsvSource(value = {"3, true", "10, true", "17, true", "24, true", "25, true", "31, true", "2, false"})
    void discount_condition(int day, boolean expected) {
        // given
        SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy();
        int dayOfMonth = createDayOfMonth(day);

        // when
        boolean result = specialDiscountPolicy.supports(dayOfMonth);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private int createDayOfMonth(int day) {
        return LocalDate.of(2023, 12, day).getDayOfMonth();
    }
}