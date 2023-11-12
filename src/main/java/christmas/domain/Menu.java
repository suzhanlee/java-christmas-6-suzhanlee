package christmas.domain;

import static christmas.domain.Menus.INPUT_MENU_EXCEPTION;

public enum Menu {

    MUSHROOM_CREAM_SOUP("애피타이저", "양송이수프", 6000),
    TAPAS("애피타이저", "타파스", 5500),
    CAESAR_SALAD("애피타이저", "시저샐러드", 8000),
    T_BONE_STEAK("메인", "티본스테이크", 55000),
    BBQ_RIB("메인", "바비큐립", 54000),
    SEAFOOD_SPAGHETTI("메인", "해산물파스타", 35000),
    CHRISTMAS_SPAGHETTI("메인", "크리스마스파스타", 25000),
    CHOCOLATE_CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    ZERO_COKE("음료", "제로콜라", 3000),
    RED_WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000);

    public static final String BEVERAGE = "음료";
    public static final String DESSERT = "디저트";
    private final String type;
    private final String name;
    private final int price;

    Menu(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public static Menu toMenu(String name) {
        for (Menu menu : Menu.values()) {
            if (isSame(name, menu)) {
                return menu;
            }
        }
        throw new IllegalStateException(INPUT_MENU_EXCEPTION);
    }

    private static boolean isSame(String name, Menu menu) {
        return menu.name.equals(name);
    }

    public boolean isBeverage() {
        return this.type.equals(BEVERAGE);
    }

    public boolean isDessert() {
        return this.type.equals(DESSERT);
    }
}
