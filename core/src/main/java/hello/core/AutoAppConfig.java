package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//설정 정보니까 @configuration 어노테이션 지정
@Configuration
@ComponentScan(
        //component scan로 다 찾는데, 거기서 뺄 걸 지정해 주는 것
        // Appconfig 있는데, 충돌나서 이렇게 한 것 같음
        // base packages 지정 안하면? : 해당 클래스 포함 패키지부터 스캔함
        //basepackages : 대상 패키지부터 스캔
        basePackages = "hello.core.member",
        // basePackageClasses : 대상 클래스 하위부터 스캔
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
