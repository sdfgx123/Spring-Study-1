package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");


    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        // String 리스트에 Bean 정보 수집
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // Bean 정보들이 담긴 bean definition 들을 하나씩 반복
        for (String beanDefinitionName : beanDefinitionNames) {
            // bean definition 정보 가지고 ac에서 bean definition 정보 가져옴
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 만약, bean definition의 역할이 application의 role과 같다면
            if (beanDefinition.getRole() == beanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        "beanDefinition = " + beanDefinition);
            }


        }

    }
}
