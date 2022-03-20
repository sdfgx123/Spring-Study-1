package hello.core.member;

import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given 이런이런 환경이 주어졌을 때 111
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when 이렇게 됐을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then 저렇게 된다
    }
}
