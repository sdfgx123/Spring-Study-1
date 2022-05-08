package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    // 메모리에 객체 올림
    MemberService memberService;
    OrderService orderService;

    // 테스트를 실행하기 전에 반드시 실행하는 것
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // primitive 타입(long으로 선언)도 가능하지만, 이렇게 하면 Null 값을 못 집어넣음.
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        // 할인 금액이 1,000원이 맞는지 검증하는 작업
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
