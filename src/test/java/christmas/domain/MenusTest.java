package christmas.domain;

import static christmas.domain.Menu.BBQ_RIB;
import static christmas.domain.Menu.CHOCOLATE_CAKE;
import static christmas.domain.Menu.T_BONE_STEAK;
import static christmas.domain.Menu.ZERO_COKE;
import static christmas.domain.Menus.ORDERED_MENU_TYPE_EXCEPTION;
import static christmas.domain.Menus.TOTAL_MENU_COUNT_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.InputMenuException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenusTest {

    @Test
    @DisplayName("메뉴 형식이 <메뉴-메뉴 개수,메뉴-메뉴 개수, ...> 와 다른 경우 예외를 던진다.")
    void menu_format_exception() {
        // given
        String menuNamesAndNumbers = "타파스-1,제로콜라-1-";

        // when // then
        assertThatThrownBy(() -> new Menus(menuNamesAndNumbers))
                .isExactlyInstanceOf(InputMenuException.class);
    }

    @Test
    @DisplayName("메뉴 형식은 <메뉴-메뉴 개수,메뉴-메뉴 개수, ...>와 같아야 한다.")
    void normal_menu_format() {
        // given
        String menuNamesAndNumbers = "타파스-1,제로콜라-1";

        // when // then
        assertThatCode(() -> new Menus(menuNamesAndNumbers))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("중복 메뉴를 입력한 경우 예외를 던진다.")
    @ValueSource(strings = {"타파스-1,제로콜라-1,타파스-1", "제로콜라-1,타파스-1,제로콜라-3"})
    void validate_duplicate_menu(String menuNamesAndNumbers) {
        // when // then
        assertThatThrownBy(() -> new Menus(menuNamesAndNumbers))
                .isExactlyInstanceOf(InputMenuException.class);
    }

    @ParameterizedTest
    @DisplayName("메뉴가 1개 미만인 경우 예외를 던진다.")
    @ValueSource(strings = {"타파스-0,제로콜라-1", "제로콜라-1,타파스-0", "양송이수프-0"})
    void validate_menu_count(String menuNamesAndNumbers) {
        // when // then
        assertThatThrownBy(() -> new Menus(menuNamesAndNumbers))
                .isExactlyInstanceOf(InputMenuException.class);
    }

    @ParameterizedTest
    @DisplayName("총 주문 메뉴가 20개 초과면 예외를 던진다.")
    @ValueSource(strings = {"타파스-20,제로콜라-1", "제로콜라-2,타파스-20", "양송이수프-21"})
    void validate_total_menu_of_orders(String menuNamesAndNumbers) {
        // when // then
        assertThatThrownBy(() -> new Menus(menuNamesAndNumbers))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage(TOTAL_MENU_COUNT_EXCEPTION);
    }

    @ParameterizedTest
    @DisplayName("음료만 주문시 예외를 던진다.")
    @ValueSource(strings = {"제로콜라-3", "제로콜라-2,레드와인-10", "샴페인-1"})
    void validate_menu_type(String menuNamesAndNumbers) {
        // when // then
        assertThatThrownBy(() -> new Menus(menuNamesAndNumbers))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage(ORDERED_MENU_TYPE_EXCEPTION);
    }

    @Test
    @DisplayName("총 주문 메뉴를 알려준다.")
    void inform_total_order_menu() {
        // given
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        // when
        Map<Menu, Integer> result = menus.informTotalOrderMenu();

        // then
        assertThat(result).isEqualTo(createExpectedMenus());
    }

    private Map<Menu, Integer> createExpectedMenus() {
        Map<Menu, Integer> expectedMenus = new HashMap<>();
        expectedMenus.put(T_BONE_STEAK, 1);
        expectedMenus.put(BBQ_RIB, 1);
        expectedMenus.put(CHOCOLATE_CAKE, 2);
        expectedMenus.put(ZERO_COKE, 1);
        return expectedMenus;
    }

    @Test
    @DisplayName("총 주문 메뉴의 디저트 개수를 구한다.")
    void count_the_total_number_of_desserts() {
        // given
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-4,제로콜라-1");

        // when
        long result = menus.totalDessertCount();

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("총 주문 메뉴의 메인 메뉴 개수를 구한다.")
    void count_the_total_number_of_mains() {
        // given
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-4,제로콜라-1");

        // when
        long result = menus.totalMainCount();

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("총 주문 금액을 구한다.")
    void calculate_total_order_amount() {
        // given
        Menus menus = new Menus("티본스테이크-1,바비큐립-1,초코케이크-2,아이스크림-4,제로콜라-1");

        // when
        long result = menus.totalOrderAmount();

        // then
        assertThat(result).isEqualTo(162000L);
    }
}
