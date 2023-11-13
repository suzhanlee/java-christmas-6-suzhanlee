package christmas.view;

public class InputView {

    public static final String INPUT_VISIT_DATE_MENT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INVALID_VISIT_DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private final ConsoleService consoleService;

    public InputView(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public int visitDate() {
        try {
            System.out.println(INPUT_VISIT_DATE_MENT);
            int visitDayOfMonth = Integer.parseInt(consoleService.readLine());
            if (visitDayOfMonth < 1 || visitDayOfMonth >= 31) {
                throw new IllegalStateException(INVALID_VISIT_DATE_EXCEPTION);
            }
            return visitDayOfMonth;
        } catch (NumberFormatException e) {
            throw new IllegalStateException(INVALID_VISIT_DATE_EXCEPTION);
        }
    }
}
