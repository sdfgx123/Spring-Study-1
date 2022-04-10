package hello.core.member;

import hello.core.discount.DiscountPolicy;

public class MemberServiceImpl implements MemberService {

    // 추상화에만 의존 > 즉, MemoryMemberRepository에 대한 내용이 없음
    private final MemberRepository memberRepository;

    // 생성자 주입
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
}
