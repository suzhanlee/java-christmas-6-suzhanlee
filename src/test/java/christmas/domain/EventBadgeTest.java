package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class EventBadgeTest {

    @ParameterizedTest
    @DisplayName("할인 금액에 따른 이벤트 배찌를 알려준다.")
    @CsvSource(value = {"5000, STAR", "19999, TREE", "20000, SANTA"})
    void inform_event_badge_according_to_discount_amount(long discountAmount, EventBadge expected) {
        // when
        EventBadge result = EventBadge.valueOf(discountAmount);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("배지의 이름을 알려준다.")
    @CsvSource(value = {"STAR, 별", "TREE, 트리", "SANTA, 산타"})
    void inform_event_badge_name(EventBadge eventBadge, String expected) {
        // when
        String result = eventBadge.getName();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
