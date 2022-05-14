package hello.core.member;

import hello.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // AppConfig를 이용해서 개발
        //AppConfig appConfig = new AppConfig();
        // memberService 구현 객체를 AppConfig에서 가져옴
        //MemberService memberService = appConfig.memberService();

        // 스프링에서 config들, 즉 구성정보들을 저장
        // 파라미터로 Appconfig 넣으면, 스프링에서 Bean 컨테이너에 객체 생성한 것들을 다 집어넣어서 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);



        Member member = new Member(1L, "memberA", Grade.VIP);
        // memberService 의 join 매서드를 통해 회원가입이 된다
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());

    }
}
