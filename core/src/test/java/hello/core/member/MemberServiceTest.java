package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // 테스트를 실행하기 전에 반드시 실행하는 것
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given 이런이런 환경이 주어졌을 때 1111111
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when 이렇게 됐을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then 저렇게 된다
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
