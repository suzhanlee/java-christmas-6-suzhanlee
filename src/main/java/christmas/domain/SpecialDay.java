package christmas.domain;

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
        for (SpecialDay specialDay : SpecialDay.values()) {
            if (specialDay.day == dayOfMonth) {
                return specialDay;
            }
        }
        return NORMAL;
    }
}
