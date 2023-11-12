package christmas.domain;

import static christmas.domain.Menus.INPUT_MENU_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenusTest {

    @Test
    @DisplayName("메뉴 형식이 <메뉴-메뉴 개수,메뉴-메뉴 개수, ...> 와 다른 경우 예외를 던진다.")
    void menu_format_exception() {
        // given
        String menuNamesAndNumbers = "타파스-1,제로콜라-1-";

        // when // then
        assertThatThrownBy(() -> new Menus(menuNamesAndNumbers))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage(INPUT_MENU_EXCEPTION);
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
}
