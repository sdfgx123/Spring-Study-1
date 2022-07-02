package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    // 스프링 없는 의존성 주입?의 문제점을 파악하기 위함
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1과 memberService2는 서로 다르다
        // Assertions까지 합쳐서 객체가 총 4개가 생성됨 -> 메모리 낭비가 심하다
        // 해결을 위해선 객체를 하나만 생성하고, 그걸 공유하게 만들면 됨
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    // 하나의 객체를 공유하게 하기 위한 -> 싱글톤 패턴을 사용하게 하기 위한 테스트
    @Test
    @DisplayName("싱글톤 패턴을 이용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 같은 인스턴스인지, 다른 인스턴스인지 확인하기 위한 코드
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
    }
}
