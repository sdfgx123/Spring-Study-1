package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10,000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B 사용자가 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA : 사용자 A 주문 금액 조회
        // int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        /*
        같은 인스턴스를 공유하기 때문에 A 사용자의 price가 10,000원이 아닌 20,000원으로 떨어지게 된다
        특정 클라이언트가 공유되는 인스턴스의 값을 변경해 버리는 게 문제
        공유필드는 정말 조심 -> 스프링 빈은 항상 무상태로 설계해야 함
         */

        // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {

        // statefulService 전용으로 Config 하나 만듬
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}