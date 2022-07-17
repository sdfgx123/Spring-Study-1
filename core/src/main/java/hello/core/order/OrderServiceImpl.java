package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// RequiredArgsConstructor : public OrderServiceImpl 코드 부분을 똑같이 만들어 줌 > 즉, 생성자를 만들어 준다는 얘기 같은데
// 즉, final 붙은 건 필수값 이니까, final을 가지고 생성자를 만들어 줌
public class OrderServiceImpl implements OrderService {

    // member Repository 에서 회원 찾아야 됨
    private final MemberRepository memberRepository;
    // 할인 정책도 필요함 > 할인 정책
    // 바로 아래 코드 보면, 인터페이스가 구체클래스도 참조하기 때문에 OCP 정책 위반임.
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 이렇게 하면 Null Pointer Exception 발생 > 해결하려면 누군가 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해 줘야함
    private final DiscountPolicy discountPolicy;

    // 여기 아래 주석된 부분 안 써도 되는 이유 : @RequiredArgsConstructor 어노테이션이 생성자를 자동으로 만들어 줬기 때문에
    // 생성자를 통해 memberRepository랑 discountPolicy가 넘어감 > 그래서 할당됨
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // member 찾아야 됨
        Member member = memberRepository.findById(memberId);
        // 잘 설계된 코드 : 나(order)는 모르겠으니 할인정책 니가 알아서 하고 나한테 파라미터 던져줘
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문을 만들어서 반환해 준다
        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
