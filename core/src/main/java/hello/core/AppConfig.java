package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig의 역할 : 애플리케이션의 전체 동작 방식을 구성(config)하기 위해 구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
// 즉, 이 클래스에서 구현 객체를 확정한다
// AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)한다. 즉 구현 객체를 직접 지정해 준다는 느낌으로 생각

/*
@Bean memberService -> new MemoryMemberRepository()
@Bean orderService -> new MemoryMemberRepository()
이렇게 되면 싱글톤이 깨지는 거 아닌가요? 왜냐면 new 인스턴스를 두 개 생성하니까

예상한 호출
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.memberRepository
call AppConfig.orderService
call AppConfig.memberRepository

실제로 호출
call AppConfig.memberService
call AppConfig.memberRepository
call AppConfig.orderService
즉, 스프링에서 어떻게든 싱글톤 패턴을 보장하는구나
 */

@Configuration
public class AppConfig {

    // Bean Annotation으로 스프링 컨테이너에 등록한다
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
