package christmas.view;

public class InputView {

    public static final String INPUT_VISIT_DATE_MENT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INVALID_VISIT_DATE_EXCEPTION = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INPUT_MENU_AND_NUMBER_MENT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final int MIN_VISIT_DAY_OF_MONTH = 1;
    public static final int MAX_VISIT_DAY_OF_MONTH = 31;
    private final ConsoleService consoleService;

    public InputView(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public int visitDayOfMonth() {
        try {
            System.out.println(INPUT_VISIT_DATE_MENT);
            int visitDayOfMonth = getVisitDayOfMonth();
            validateDayOfMonth(visitDayOfMonth);
            return visitDayOfMonth;
        } catch (NumberFormatException e) {
            throw new IllegalStateException(INVALID_VISIT_DATE_EXCEPTION);
        }
    }

    private int getVisitDayOfMonth() {
        return Integer.parseInt(consoleService.readLine());
    }

    private void validateDayOfMonth(int visitDayOfMonth) {
        if (isDayOfMonth(visitDayOfMonth)) {
            throw new IllegalStateException(INVALID_VISIT_DATE_EXCEPTION);
        }
    }

    private boolean isDayOfMonth(int visitDayOfMonth) {
        return visitDayOfMonth < MIN_VISIT_DAY_OF_MONTH || visitDayOfMonth >= MAX_VISIT_DAY_OF_MONTH;
    }

    public String menuAndNumber() {
        System.out.println(INPUT_MENU_AND_NUMBER_MENT);
        return consoleService.readLine();
    }
}
