package christmas.exception;

public class OrderMenuTypeException extends IllegalStateException {

    public static final String ORDER_MENU_TYPE_EXCEPTION = "[ERROR] 주문시 음료만 주문할 수 없습니다.";

    public OrderMenuTypeException() {
        super(ORDER_MENU_TYPE_EXCEPTION);
    }
}
