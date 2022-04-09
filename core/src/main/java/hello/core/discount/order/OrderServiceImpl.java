package hello.core.discount.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // member Repository 에서 회원 찾아야 됨
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인 정책도 필요함 > 할인 정책
    // 바로 아래 코드 보면, 인터페이스가 구체클래스도 참조하기 때문에 OCP 정책 위반임.
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // member 찾아야 됨
        Member member = memberRepository.findById(memberId);
        // 잘 설계된 코드 : 나(order)는 모르겠으니 할인정책 니가 알아서 하고 나한테 파라미터 던져줘
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문을 만들어서 반환해 준다
        return new Order(memberId, itemName, itemPrice, discountPrice);

    }
}
