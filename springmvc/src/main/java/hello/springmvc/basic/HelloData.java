package hello.springmvc.basic;

import lombok.Data;

// @Data 쓰면 getter, setter, tostring 등등 자동으로 적용해줌
@Data
public class HelloData {
    private String username;
    private int age;
}
