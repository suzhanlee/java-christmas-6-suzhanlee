package christmas.domain;

public enum EventBadge {
    STAR("별", 5000, 10000),
    TREE("트리", 10000, 20000),
    SANTA("산타", 20000, Long.MAX_VALUE),
    NOTHING("없음", -1, -1);

    private final String name;
    private final long leastDiscountAmount;
    private final long maxDiscountAmount;

    EventBadge(String name, long leastDiscountAmount, long maxDiscountAmount) {
        this.name = name;
        this.leastDiscountAmount = leastDiscountAmount;
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public static EventBadge valueOf(long discountAmount) {
        for (EventBadge eventBadge : EventBadge.values()) {
            if (discountAmount >= eventBadge.leastDiscountAmount && discountAmount < eventBadge.maxDiscountAmount) {
                return eventBadge;
            }
        }
        return NOTHING;
    }

    public String getName() {
        return this.name;
    }
}
