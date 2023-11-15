package christmas.dto;

import christmas.domain.EventBadge;

public class EventBadgeDto {

    private final String name;

    private EventBadgeDto(String name) {
        this.name = name;
    }

    public static EventBadgeDto valueOf(EventBadge eventBadge) {
        return new EventBadgeDto(eventBadge.getName());
    }
}
