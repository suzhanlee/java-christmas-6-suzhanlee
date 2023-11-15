package christmas.domain.discount.policy;

import static christmas.domain.discount.policy.SpecialDiscountPolicy.SPECIAL_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Menus;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpecialDiscountPolicyTest {

    @ParameterizedTest
    @DisplayName("이벤트 달력에 별이 있으면 할인을 적용한다.")
    @CsvSource(value = {"3, true", "10, true", "17, true", "24, true", "25, true", "31, true", "2, false"})
    void discount_condition(int day, boolean expected) {
        // given
        LocalDate visitDate = LocalDate.of(2023, 12, day);
        SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy(visitDate);

        // when
        boolean result = specialDiscountPolicy.supports();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("총 주문 금액에서 1000원 할인한다.")
    void calculate_discount_amount() {
        // given
        LocalDate visitDate = LocalDate.of(2023, 12, 10);
        SpecialDiscountPolicy specialDiscountPolicy = new SpecialDiscountPolicy(visitDate);

        // when
        Map<String, Long> result = specialDiscountPolicy.calculateDiscountAmount(createMenus());

        // then
        assertThat(result).isEqualTo(createExpected());
    }

    private Menus createMenus() {
        return new Menus("해산물파스타-1");
    }

    private Map<String, Long> createExpected() {
        Map<String, Long> expected = new HashMap<>();
        expected.put(SPECIAL_DISCOUNT, 1000L);
        return expected;
    }
}
