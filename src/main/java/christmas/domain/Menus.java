package christmas.domain;

import java.util.regex.Pattern;

public class Menus {
    public static final String MENU_REGEX = "([가-힣]+-\\d,?)+";
    public static final String INPUT_MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public Menus(String menuForm) {
        validateMenuFormat(menuForm);
    }

    private void validateMenuFormat(String menuForm) {
        if (!matchable(menuForm, createMenuPattern())) {
            throw new IllegalStateException(INPUT_MENU_EXCEPTION);
        }
    }

    private boolean matchable(String menuForm, Pattern pattern) {
        return pattern.matcher(menuForm).matches();
    }

    private Pattern createMenuPattern() {
        return Pattern.compile(MENU_REGEX);
    }
}
