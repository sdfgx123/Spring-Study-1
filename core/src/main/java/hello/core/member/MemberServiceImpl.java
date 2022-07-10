package hello.core.member;

import hello.core.discount.DiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 추상화에만 의존 > 즉, MemoryMemberRepository에 대한 내용이 없음
    private final MemberRepository memberRepository;

    // 생성자 주입
    // @autowired : 의존관계 자동 주입이 필요함 > 생성자에 붙여줌
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
