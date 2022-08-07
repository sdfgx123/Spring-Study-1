package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// Lombok 라이브러리를 통해 getter setter를 따로 만들어줄 필요 없음
@Getter @Setter
public class HelloData {

    private String username;
    private int age;
}
