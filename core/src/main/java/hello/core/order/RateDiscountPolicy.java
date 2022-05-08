package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    // 할인율 int로 선언
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            // 10% 할인된 금액을 계산하는 로직
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
