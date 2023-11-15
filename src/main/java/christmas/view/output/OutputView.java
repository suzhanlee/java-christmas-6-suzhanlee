package christmas.view.output;

import static christmas.view.output.OutputViewConstant.AMOUNT_PATTERN;
import static christmas.view.output.OutputViewConstant.COLUMN_SPACE;
import static christmas.view.output.OutputViewConstant.ENTER;
import static christmas.view.output.OutputViewConstant.MINUS;
import static christmas.view.output.OutputViewConstant.NONE_EVENT_BADGE;
import static christmas.view.output.OutputViewConstant.NOTHING;
import static christmas.view.output.OutputViewConstant.NUMBER;
import static christmas.view.output.OutputViewConstant.SPACE;
import static christmas.view.output.OutputViewConstant.WON;
import static christmas.view.output.OutputViewConstant.ZERO;
import static christmas.view.output.OutputViewConstant.ZERO_WON;
import static christmas.view.output.OutputViewMessage.BENEFIT_DETAILS_MESSAGE;
import static christmas.view.output.OutputViewMessage.DECEMBER_EVENT_BADGE_MESSAGE;
import static christmas.view.output.OutputViewMessage.ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT_MESSAGE;
import static christmas.view.output.OutputViewMessage.EVENT_BENEFIT_PREVIEW_MESSAGE;
import static christmas.view.output.OutputViewMessage.GIVE_AWAY_MENU_MESSAGE;
import static christmas.view.output.OutputViewMessage.ORDER_MENU_MESSAGE;
import static christmas.view.output.OutputViewMessage.TOTAL_BENEFIT_AMOUNT_MESSAGE;
import static christmas.view.output.OutputViewMessage.TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_MESSAGE;

import christmas.dto.GiftDto;
import christmas.dto.MenuDto;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputView {

    public void printEventBenefitPreviewMessage(int visitDayOfMonth) {
        System.out.printf(EVENT_BENEFIT_PREVIEW_MESSAGE, visitDayOfMonth);
    }

    public void printOrderMenus(List<MenuDto> menuDtos) {
        System.out.println(ORDER_MENU_MESSAGE);
        System.out.println(createOrderMenus(menuDtos));
    }

    private StringJoiner createOrderMenus(List<MenuDto> menuDtos) {
        StringJoiner orderMenus = new StringJoiner(ENTER);
        menuDtos.forEach(menuDto -> addOrderMenuTo(menuDto, orderMenus));
        return orderMenus;
    }

    private void addOrderMenuTo(MenuDto menuDto, StringJoiner orderMenu) {
        orderMenu.add(menuDto.getName() + SPACE + menuDto.getQuantity() + NUMBER);
    }

    public void printTotalOrderAmount(long totalOrderAmount) {
        System.out.println(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(getAmountFormat().format(totalOrderAmount) + WON);
    }

    private DecimalFormat getAmountFormat() {
        return new DecimalFormat(AMOUNT_PATTERN);
    }

    public void printGifts(List<GiftDto> giftDtos) {
        System.out.println(GIVE_AWAY_MENU_MESSAGE);

        if (giftDtos.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }

        System.out.println(createGifts(giftDtos));
    }

    private StringBuilder createGifts(List<GiftDto> giftDtos) {
        StringBuilder gifts = new StringBuilder();
        giftDtos.forEach(giftDto -> addGiftTo(giftDto, gifts));
        return gifts;
    }

    private void addGiftTo(GiftDto giftDto, StringBuilder gifts) {
        gifts.append(giftDto.getName()).append(SPACE).append(giftDto.getQuantity()).append(NUMBER).append(ENTER);
    }

    public void printBenefitDetails(Map<String, Long> benefitDetails) {
        System.out.println(BENEFIT_DETAILS_MESSAGE);
        if (benefitDetails.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        System.out.println(createBenefitDetails(benefitDetails, getAmountFormat()));
    }

    private StringBuilder createBenefitDetails(Map<String, Long> benefitDetails, DecimalFormat discountAmountFormat) {
        StringBuilder benefitDetailStorage = new StringBuilder();
        benefitDetails.entrySet().forEach(
                benefitDetail -> addBenefitDetailTo(benefitDetail, benefitDetailStorage, discountAmountFormat));
        return benefitDetailStorage;
    }

    private void addBenefitDetailTo(Entry<String, Long> benefitDetail, StringBuilder benefitDetailStorage,
                                    DecimalFormat discountAmountFormat) {
        String discountType = benefitDetail.getKey();
        Long discountAmount = benefitDetail.getValue();
        benefitDetailStorage.append(discountType).append(COLUMN_SPACE).append(MINUS)
                .append(discountAmountFormat.format(discountAmount)).append(WON).append(ENTER);
    }

    public void printTotalDiscountAmount(long totalDiscountAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT_MESSAGE);
        if (isZero(totalDiscountAmount)) {
            System.out.println(ZERO_WON);
            return;
        }
        System.out.println(MINUS + getAmountFormat().format(totalDiscountAmount) + WON);
    }

    private boolean isZero(long totalDiscountAmount) {
        return totalDiscountAmount == ZERO;
    }

    public void printExpectedPaymentAmount(long expectedPaymentAmount) {
        System.out.println(ESTIMATED_PAYMENT_AMOUNT_AFTER_DISCOUNT_MESSAGE);
        System.out.println(getAmountFormat().format(expectedPaymentAmount) + WON);
    }

    public void printEventBadge(String eventBadge) {
        System.out.println(DECEMBER_EVENT_BADGE_MESSAGE);
        if (isExist(eventBadge)) {
            System.out.println(eventBadge);
            return;
        }
        System.out.println(NOTHING);
    }

    private boolean isExist(String eventBadge) {
        return NONE_EVENT_BADGE.equals(eventBadge);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
