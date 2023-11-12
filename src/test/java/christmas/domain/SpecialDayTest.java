package christmas.domain;

import static christmas.domain.SpecialDay.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDayTest {

    @ParameterizedTest
    @DisplayName("날짜를 별이 있는 날짜로 바꾼다.")
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void day_of_month_to_special_day(int dayOfMonth) {
        // when
        SpecialDay result = SpecialDay.valueOf(dayOfMonth);

        // then
        assertThat(result).isInstanceOf(SpecialDay.class);
        assertThat(result).isNotEqualTo(NORMAL);
    }

    @ParameterizedTest
    @DisplayName("날짜를 평범한 날짜로 바꾼다.")
    @ValueSource(ints = {1, 4, 11, 18, 26, 30})
    void day_of_month_to_normal(int dayOfMonth) {
        // when
        SpecialDay result = SpecialDay.valueOf(dayOfMonth);

        // then
        assertThat(result).isEqualTo(NORMAL);
    }

}