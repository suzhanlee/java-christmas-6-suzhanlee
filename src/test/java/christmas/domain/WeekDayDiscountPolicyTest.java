package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("디저트 메뉴를 메뉴 1개당 2023원 할인한다.")
    void calculate_discount_amount() {
        // given
        WeekDayDiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy();
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        // when
        long result = weekDayDiscountPolicy.calculateDiscountAmount(menus);

        // then
        assertThat(result).isEqualTo(4046);
    }
}
