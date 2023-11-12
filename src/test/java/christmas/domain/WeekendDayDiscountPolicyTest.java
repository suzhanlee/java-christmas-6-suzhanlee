package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("메인 메뉴를 메뉴 1개당 2023원 할인한다.")
    void calculate_discount_amount() {
        // given
        WeekendDayDiscountPolicy weekendDayDiscountPolicy = new WeekendDayDiscountPolicy();
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        // when
        long result = weekendDayDiscountPolicy.calculateDiscountAmount(menus);

        // then
        assertThat(result).isEqualTo(4046);
    }
}
