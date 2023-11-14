package christmas.exception;

public class InputMenuException extends IllegalStateException {

    public static final String INPUT_MENU_EXCEPTION = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public InputMenuException() {
        super(INPUT_MENU_EXCEPTION);
    }
}
