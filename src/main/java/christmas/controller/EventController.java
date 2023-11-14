package christmas.controller;

import christmas.domain.EventBadge;
import christmas.domain.Menus;
import christmas.domain.OrderedMenuInfo;
import christmas.domain.WooWaRestaurant;
import christmas.domain.discount.DiscountAmount;
import christmas.domain.discount.GiftEvent;
import christmas.domain.discount.policy.ChristmasDDayDiscountPolicy;
import christmas.domain.discount.policy.DiscountPolicy;
import christmas.domain.discount.policy.SpecialDiscountPolicy;
import christmas.domain.discount.policy.WeekDayDiscountPolicy;
import christmas.domain.discount.policy.WeekendDiscountPolicy;
import christmas.view.OutputView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventController {
    public static final int DISCOUNT_YEAR = 2023;
    public static final int DISCOUNT_MONTH = 12;
    public static final String NONE_EVENT_BADGE = "없음";
    private final OutputView outputView;

    public EventController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void startEvent(int visitDayOfMonth, String menuForm) {
        LocalDate visitDate = createVisitDate(visitDayOfMonth);
        WooWaRestaurant wooWaRestaurant = createWooWaRestaurant(visitDate);
        Menus menus = wooWaRestaurant.findMenusBy(menuForm);
        OrderedMenuInfo menuInfo = wooWaRestaurant.order(visitDate, menus);

        printEventBenefitsPreview(visitDayOfMonth, menus, menuInfo);
    }

    private LocalDate createVisitDate(int visitDayOfMonth) {
        return LocalDate.of(DISCOUNT_YEAR, DISCOUNT_MONTH, visitDayOfMonth);
    }

    private WooWaRestaurant createWooWaRestaurant(LocalDate visitDate) {
        return new WooWaRestaurant(new DiscountAmount(createDiscountPolicies(visitDate), new GiftEvent()));
    }

    private List<DiscountPolicy> createDiscountPolicies(LocalDate visitDate) {
        List<DiscountPolicy> discountPolicies = new ArrayList<>();
        discountPolicies.add(new ChristmasDDayDiscountPolicy(visitDate));
        discountPolicies.add(new WeekDayDiscountPolicy(visitDate));
        discountPolicies.add(new WeekendDiscountPolicy(visitDate));
        discountPolicies.add(new SpecialDiscountPolicy(visitDate));
        return discountPolicies;
    }

    private void printEventBenefitsPreview(int visitDayOfMonth, Menus menus, OrderedMenuInfo menuInfo) {
        outputView.printEventBenefitPreviewMessage(visitDayOfMonth);
        outputView.printOrderMenu(menus);
        outputView.printTotalOrderAmount(menus.totalOrderAmount());
        outputView.printGifts(menuInfo.gifts());
        outputView.printBenefitDetails(menuInfo.benefitDetails());
        outputView.printTotalDiscountAmount(menuInfo.totalDiscountAmount());
        outputView.printExpectedPaymentAmount(menuInfo.expectedPaymentAmount());
        outputView.printEventBadge(createEventBadge(menuInfo.eventBadge()));
    }

    private String createEventBadge(EventBadge eventBadge) {
        if (isExist(eventBadge)) {
            return eventBadge.getName();
        }
        return NONE_EVENT_BADGE;
    }

    private boolean isExist(EventBadge eventBadge) {
        return eventBadge != EventBadge.NOTHING;
    }
}
