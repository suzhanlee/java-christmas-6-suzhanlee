package christmas.domain.discount.policy;

import static christmas.domain.discount.policy.WeekDayDiscountPolicy.WEEK_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menus;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
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
        WeekDayDiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy(createLocalDateBy(dayOfWeek));

        // when
        boolean result = weekDayDiscountPolicy.supports();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private LocalDate createLocalDateBy(DayOfWeek dayOfWeek) {
        return LocalDate.of(2023, 12, 13).with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }

    @Test
    @DisplayName("디저트 메뉴를 메뉴 1개당 2023원 할인한다.")
    void calculate_discount_amount() {
        // given
        LocalDate localDate = LocalDate.of(2023, 12, 12);
        WeekDayDiscountPolicy weekDayDiscountPolicy = new WeekDayDiscountPolicy(localDate);
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        // when
        Map<String, Long> result = weekDayDiscountPolicy.calculateDiscountAmount(menus);

        // then
        assertThat(result).isEqualTo(createExpected());
    }

    private Map<String, Long> createExpected() {
        Map<String, Long> expected = new HashMap<>();
        expected.put(WEEK_DAY_DISCOUNT, 4046L);
        return expected;
    }
}
