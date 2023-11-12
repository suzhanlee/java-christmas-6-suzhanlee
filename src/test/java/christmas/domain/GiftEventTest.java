package christmas.domain;

import static christmas.domain.Menu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @DisplayName("증정 이벤트로 샴페인 1개를 제공한다.")
    void give_champagne_for_gift_event() {
        // given
        GiftEvent giftEvent = new GiftEvent();

        // when
        Map<Menu, Integer> result = giftEvent.giveGift();

        // then
        assertThat(result.containsKey(CHAMPAGNE)).isTrue();
        assertThat(result.get(CHAMPAGNE)).isEqualTo(1);
        assertThat(result.size()).isEqualTo(1);
    }
}
