package christmas.domain.discount.policy;

import java.util.Arrays;

public enum SpecialDay {

    THREE(3),
    TEN(10),
    SEVENTEEN(17),
    TWENTY_FOUR(24),
    TWENTY_FIVE(25),
    THIRTY_ONE(31),
    NORMAL(-1);

    private final int day;

    SpecialDay(int day) {
        this.day = day;
    }

    public static SpecialDay valueOf(int dayOfMonth) {
        return Arrays.stream(SpecialDay.values()).filter(specialDay -> matchable(dayOfMonth, specialDay)).findFirst().orElse(NORMAL);
    }

    private static boolean matchable(int dayOfMonth, SpecialDay specialDay) {
        return specialDay.day == dayOfMonth;
    }
}
