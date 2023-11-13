package christmas.domain;

import static christmas.domain.Menus.INPUT_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @ParameterizedTest
    @DisplayName("메뉴 이름으로 메뉴를 가져온다")
    @CsvSource(value = {"양송이수프, MUSHROOM_CREAM_SOUP", "해산물파스타, SEAFOOD_SPAGHETTI", "제로콜라, ZERO_COKE"})
    void find_menu_by_menu_name(String menuName, Menu expected) {
        // when // then
        assertThat(Menu.toMenu(menuName)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴에 메뉴 이름이 없으면 예외를 던진다.")
    @ValueSource(strings = {"쇠고기수프", "닭갈비"})
    void find_menu_exception(String menuName) {
        // when // then
        assertThatThrownBy(() -> Menu.toMenu(menuName))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage(INPUT_MENU_EXCEPTION);
    }

    @ParameterizedTest
    @DisplayName("메뉴의 종류가 음료인지 아닌지 알려준다.")
    @CsvSource(value = {"RED_WINE, true", "MUSHROOM_CREAM_SOUP, false"})
    void inform_menu_type_that_is_beverage_or_not(Menu menu, boolean expected) {
        // when
        boolean result = menu.isBeverage();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴의 종류가 디저트인지 아닌지 알려준다.")
    @CsvSource(value = {"ICE_CREAM, true", "RED_WINE, false"})
    void inform_menu_type_that_is_dessert_or_not(Menu menu, boolean expected) {
        // when
        boolean result = menu.isDessert();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("메뉴의 종류가 메인 메뉴인지 아닌지 알려준다.")
    @CsvSource(value = {"T_BONE_STEAK, true", "ICE_CREAM, false"})
    void inform_menu_type_that_is_main_or_not(Menu menu, boolean expected) {
        // when
        boolean result = menu.isMain();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("주문한 특정 메뉴의 갯수로 특정 메뉴의 총 주문 가격을 알려준다.")
    @CsvSource(value = {"MUSHROOM_CREAM_SOUP, 2, 12000", "SEAFOOD_SPAGHETTI, 1, 35000", "CHOCOLATE_CAKE, 4, 60000"})
    void calculate_total_menu_price(Menu menu, int menuNumber, long expected) {
        // when
        long result = menu.totalMenuPrice(menuNumber);

        // then
        assertThat(result).isEqualTo(expected);
    }
}