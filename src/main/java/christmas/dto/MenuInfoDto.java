package christmas.dto;

import java.util.List;
import java.util.Map;

public class MenuInfoDto {

    private final List<GiftDto> giftDtos;
    private final Map<String, Long> benefitDetailsDto;
    private final long totalDiscountAmount;
    private final long expectedPaymentAmount;
    private final EventBadgeDto eventBadgeDto;

    public MenuInfoDto(List<GiftDto> giftDtos, Map<String, Long> benefitDetailsDto, long totalDiscountAmount,
                       long expectedPaymentAmount, EventBadgeDto eventBadgeDto) {
        this.giftDtos = giftDtos;
        this.benefitDetailsDto = benefitDetailsDto;
        this.totalDiscountAmount = totalDiscountAmount;
        this.expectedPaymentAmount = expectedPaymentAmount;
        this.eventBadgeDto = eventBadgeDto;
    }

    public List<GiftDto> getGiftDtos() {
        return giftDtos;
    }

    public Map<String, Long> getBenefitDetails() {
        return benefitDetailsDto;
    }

    public long getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public long getExpectedPaymentAmount() {
        return expectedPaymentAmount;
    }

    public EventBadgeDto getEventBadgeDto() {
        return eventBadgeDto;
    }
}
