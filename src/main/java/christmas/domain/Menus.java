package christmas.domain;

import christmas.exception.InputMenuException;
import christmas.exception.OrderMenuTypeException;
import christmas.exception.TotalMenuNumberLimitException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Menus {
    public static final String MENU_REGEX = "([가-힣]+-\\d+,?)+";
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
        validateMenusCount(uniqueMenus);
        validateMenuTotalMenuCount(uniqueMenus);
        validateOrderedMenuType(uniqueMenus);
        return uniqueMenus;
    }

    private void validateMenuFormat(String menuForm) {
        if (!matchable(menuForm, createMenuPattern())) {
            throw new InputMenuException();
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
            throw new InputMenuException();
        }
    }

    private boolean isSame(int actualMenuSize, int expectedMenuSize) {
        return actualMenuSize != expectedMenuSize;
    }

    private void validateMenusCount(Map<Menu, Integer> uniqueMenus) {
        uniqueMenus.values().forEach(this::validateMenuCount);
    }

    private void validateMenuCount(Integer menuCount) {
        if (!existMenuOrder(menuCount)) {
            throw new InputMenuException();
        }
    }

    private boolean existMenuOrder(Integer menuCount) {
        return menuCount >= 1;
    }

    private void validateMenuTotalMenuCount(Map<Menu, Integer> uniqueMenus) {
        if (greaterThanTwenty(uniqueMenus)) {
            throw new TotalMenuNumberLimitException();
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
            throw new OrderMenuTypeException();
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

    public long totalMainCount() {
        return menus.keySet().stream().filter(Menu::isMain).mapToLong(this.menus::get).sum();
    }

    public long totalOrderAmount() {
        return menus.entrySet().stream().mapToLong(this::calculateTotalMenuPrice).sum();
    }

    private long calculateTotalMenuPrice(Entry<Menu, Integer> menuAndNumber) {
        Menu menu = menuAndNumber.getKey();
        return menu.totalMenuPrice(menuAndNumber.getValue());
    }
}
