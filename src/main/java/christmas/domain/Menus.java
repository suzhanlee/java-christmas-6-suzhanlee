package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Menus {
    public static final String MENU_REGEX = "([가-힣]+-\\d,?)+";
    public static final String INPUT_MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String DASH = "-";
    public static final int DEFAULT_MENU_NUMBER_VALUE = 0;
    public static final int MENU_INDEX = 0;
    public static final int MENU_NUMBER_INDEX = 1;
    public static final String COMMA = ",";
    private final Map<Menu, Integer> menus;

    public Menus(String menuForm) {
        this.menus = createMenus(menuForm);
    }

    private Map<Menu, Integer> createMenus(String menuForm) {
        validateMenuFormat(menuForm);
        List<String> overlappedMenus = split(menuForm);
        Map<Menu, Integer> uniqueMenus = createUniqueMenus(overlappedMenus);
        validateDuplication(uniqueMenus, overlappedMenus);
        return uniqueMenus;
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

    private void validateDuplication(Map<Menu, Integer> menus, List<String> menusAndNumbers) {
        if (isSame(menus.size(), menusAndNumbers.size())) {
            throw new IllegalStateException(INPUT_MENU_EXCEPTION);
        }
    }

    private List<String> split(String menuForm) {
        return List.of(menuForm.split(COMMA));
    }

    private boolean isSame(int actualMenuSize, int expectedMenuSize) {
        return actualMenuSize != expectedMenuSize;
    }

    private Map<Menu, Integer> createUniqueMenus(List<String> menusAndNumbers) {
        Map<Menu, Integer> menus = new HashMap<>();
        menusAndNumbers.forEach(menuAndNumber -> addMenuAndNumber(separateMenuAndNumber(menuAndNumber), menus));
        return menus;
    }

    private List<String> separateMenuAndNumber(String menuAndNumber) {
        return List.of(menuAndNumber.split(DASH));
    }

    private void addMenuAndNumber(List<String> seperatedMenuAndNumber, Map<Menu, Integer> menuMap) {
        Menu menu = Menu.toMenu(seperatedMenuAndNumber.get(MENU_INDEX));
        int menuNumber = Integer.parseInt(seperatedMenuAndNumber.get(MENU_NUMBER_INDEX));
        menuMap.put(menu, menuMap.getOrDefault(menu, DEFAULT_MENU_NUMBER_VALUE) + menuNumber);
    }
}
