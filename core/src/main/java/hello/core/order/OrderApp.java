package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        // OrderApp도 AppConfig를 쓰도록 바꿈
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        //   MemberService memberService = new MemberServiceImpl();
     //   OrderService orderService = new OrderServiceImpl( );

        // member id를 1로 지정
        Long memberId = 1L;
        // 회원 선언
        Member member = new Member(memberId, "memberA", Grade.VIP);
        // 메모리 객체에 해당 member 삽입
        memberService.join(member);

        // order 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice = " + order.calculatePrice());



    }
}
