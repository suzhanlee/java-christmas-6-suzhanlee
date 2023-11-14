package christmas.exception;

public class TotalMenuNumberLimitException extends IllegalStateException {

    public static final String TOTAL_MENU_NUMBER_LIMIT_EXCEPTION = "[ERROR] 한번에 주문할 수 있는 메뉴는 20개를 넘길 수 없습니다.";

    public TotalMenuNumberLimitException() {
        super(TOTAL_MENU_NUMBER_LIMIT_EXCEPTION);
    }
}
