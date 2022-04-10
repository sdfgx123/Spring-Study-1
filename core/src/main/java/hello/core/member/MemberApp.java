package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig를 이용해서 개발
        AppConfig appConfig = new AppConfig();
        // memberService 구현 객체를 AppConfig에서 가져옴
        MemberService memberService = appConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        // memberService 의 join 매서드를 통해 회원가입이 된다
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
