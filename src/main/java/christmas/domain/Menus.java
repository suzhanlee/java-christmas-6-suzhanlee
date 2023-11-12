package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Menus {
    public static final String MENU_REGEX = "([가-힣]+-\\d+,?)+";
    public static final String INPUT_MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String DASH = "-";
    public static final int DEFAULT_MENU_NUMBER_VALUE = 0;
    public static final int MENU_INDEX = 0;
    public static final int MENU_NUMBER_INDEX = 1;
    public static final String COMMA = ",";
    public static final String TOTAL_MENU_COUNT_EXCEPTION = "[ERROR] 한번에 주문할 수 있는 메뉴는 20개를 넘길 수 없습니다.";
    public static final String ORDERED_MENU_TYPE_EXCEPTION = "[ERROR] 주문시 음료만 주문할 수 없습니다.";
    private final Map<Menu, Integer> menus;

    public Menus(String menuForm) {
        this.menus = createMenus(menuForm);
    }

    private Map<Menu, Integer> createMenus(String menuForm) {
        validateMenuFormat(menuForm);
        List<String> overlappedMenus = split(menuForm);
        Map<Menu, Integer> uniqueMenus = createUniqueMenus(overlappedMenus);
        validateDuplication(uniqueMenus, overlappedMenus);
        validateMenuCount(uniqueMenus);
        validateMenuTotalMenuCount(uniqueMenus);
        validateOrderedMenuType(uniqueMenus);
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

    private List<String> split(String menuForm) {
        return List.of(menuForm.split(COMMA));
    }

    private Map<Menu, Integer> createUniqueMenus(List<String> menusAndNumbers) {
        Map<Menu, Integer> menus = new HashMap<>();
        menusAndNumbers.forEach(menuAndNumber -> addMenuAndNumber(separateMenuAndNumber(menuAndNumber), menus));
        return menus;
    }

    private void addMenuAndNumber(List<String> seperatedMenuAndNumber, Map<Menu, Integer> menuMap) {
        Menu menu = Menu.toMenu(seperatedMenuAndNumber.get(MENU_INDEX));
        int menuNumber = Integer.parseInt(seperatedMenuAndNumber.get(MENU_NUMBER_INDEX));
        menuMap.put(menu, menuMap.getOrDefault(menu, DEFAULT_MENU_NUMBER_VALUE) + menuNumber);
    }

    private List<String> separateMenuAndNumber(String menuAndNumber) {
        return List.of(menuAndNumber.split(DASH));
    }

    private void validateDuplication(Map<Menu, Integer> menus, List<String> menusAndNumbers) {
        if (isSame(menus.size(), menusAndNumbers.size())) {
            throw new IllegalStateException(INPUT_MENU_EXCEPTION);
        }
    }

    private boolean isSame(int actualMenuSize, int expectedMenuSize) {
        return actualMenuSize != expectedMenuSize;
    }

    private void validateMenuCount(Map<Menu, Integer> uniqueMenus) {
        for (Integer menuCount : uniqueMenus.values()) {
            if (!existMenuOrder(menuCount)) {
                throw new IllegalStateException(INPUT_MENU_EXCEPTION);
            }
        }
    }

    private boolean existMenuOrder(Integer menuCount) {
        return menuCount >= 1;
    }

    private void validateMenuTotalMenuCount(Map<Menu, Integer> uniqueMenus) {
        if (greaterThanTwenty(uniqueMenus)) {
            throw new IllegalStateException(TOTAL_MENU_COUNT_EXCEPTION);
        }
    }

    private boolean greaterThanTwenty(Map<Menu, Integer> uniqueMenus) {
        return getTotalMenuCount(uniqueMenus) > 20;
    }

    private int getTotalMenuCount(Map<Menu, Integer> uniqueMenus) {
        return uniqueMenus.values().stream().mapToInt(menuCount -> menuCount).sum();
    }

    private void validateOrderedMenuType(Map<Menu, Integer> uniqueMenus) {
        if (unableToOrder(uniqueMenus)) {
            throw new IllegalStateException(ORDERED_MENU_TYPE_EXCEPTION);
        }
    }

    private boolean unableToOrder(Map<Menu, Integer> uniqueMenus) {
        for (Menu menu : uniqueMenus.keySet()) {
            if (isNotBeverage(menu)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotBeverage(Menu menu) {
        return !menu.isBeverage();
    }

    public Map<Menu, Integer> informTotalOrderMenu() {
        return this.menus;
    }

    public long totalDessertCount() {
        return menus.keySet().stream().filter(Menu::isDessert).mapToLong(this.menus::get).sum();
    }
}
