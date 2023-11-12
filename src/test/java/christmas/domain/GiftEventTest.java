package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GiftEventTest {

    @ParameterizedTest
    @DisplayName("할인 전 총 주문 금액이 12만원 이상인지 확인한다.")
    @CsvSource(value = {"12000, true", "11999, false"})
    void gift_condition(long totalOrderAmount, boolean expected) {
        // given
        GiftEvent giftEvent = new GiftEvent();

        // when
        boolean result = giftEvent.supports(totalOrderAmount);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
