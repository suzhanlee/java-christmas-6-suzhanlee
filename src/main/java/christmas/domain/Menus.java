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
    public static final int DEFAULT_MENU_QUANTITY_VALUE = 0;
    public static final int MENU_INDEX = 0;
    public static final int MENU_QUANTITY_INDEX = 1;
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
        validateMenusQuantity(uniqueMenus);
        validateMenuTotalMenuQuantity(uniqueMenus);
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

    private Map<Menu, Integer> createUniqueMenus(List<String> menusAndQuantity) {
        Map<Menu, Integer> uniqueMenus = new HashMap<>();
        menusAndQuantity.forEach(menuAndQuantity -> addMenuAndQuantity(separateMenuAndQuantity(menuAndQuantity), uniqueMenus));
        return uniqueMenus;
    }

    private void addMenuAndQuantity(List<String> seperatedMenuAndQuantity, Map<Menu, Integer> uniqueMenus) {
        Menu menu = Menu.toMenu(seperatedMenuAndQuantity.get(MENU_INDEX));
        int quantity = Integer.parseInt(seperatedMenuAndQuantity.get(MENU_QUANTITY_INDEX));
        uniqueMenus.put(menu, uniqueMenus.getOrDefault(menu, DEFAULT_MENU_QUANTITY_VALUE) + quantity);
    }

    private List<String> separateMenuAndQuantity(String menuAndQuantity) {
        return List.of(menuAndQuantity.split(DASH));
    }

    private void validateDuplication(Map<Menu, Integer> menus, List<String> menusAndQuantity) {
        if (isSame(menus.size(), menusAndQuantity.size())) {
            throw new InputMenuException();
        }
    }

    private boolean isSame(int actualMenuSize, int expectedMenuSize) {
        return actualMenuSize != expectedMenuSize;
    }

    private void validateMenusQuantity(Map<Menu, Integer> uniqueMenus) {
        uniqueMenus.values().forEach(this::validateMenuQuantity);
    }

    private void validateMenuQuantity(int quantity) {
        if (!existMenuOrder(quantity)) {
            throw new InputMenuException();
        }
    }

    private boolean existMenuOrder(int quantity) {
        return quantity >= 1;
    }

    private void validateMenuTotalMenuQuantity(Map<Menu, Integer> uniqueMenus) {
        if (greaterThanTwenty(uniqueMenus)) {
            throw new TotalMenuNumberLimitException();
        }
    }

    private boolean greaterThanTwenty(Map<Menu, Integer> uniqueMenus) {
        return getTotalMenuQuantity(uniqueMenus) > 20;
    }

    private int getTotalMenuQuantity(Map<Menu, Integer> uniqueMenus) {
        return uniqueMenus.values().stream().mapToInt(menuQuantity -> menuQuantity).sum();
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

    private long calculateTotalMenuPrice(Entry<Menu, Integer> menuAndQuantity) {
        Menu menu = menuAndQuantity.getKey();
        return menu.totalMenuPrice(menuAndQuantity.getValue());
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }
}
