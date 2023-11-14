package christmas.exception;

public class InvalidVisitDateException extends IllegalArgumentException {

    public static final String INVALID_VISIT_DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public InvalidVisitDateException() {
        super(INVALID_VISIT_DATE_EXCEPTION);
    }
}
