package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // member Repository 에서 회원 찾아야 됨
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 할인 정책도 필요함
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
