package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        // member id를 1로 지정
        Long memberId = 1L;
        // 회원 생성
        Member member = new Member(memberId, "memberA", Grade.VIP);
        // 메모리 객체에 해당 member를 넣음
        memberService.join(member);

        // order 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());

    }
}
