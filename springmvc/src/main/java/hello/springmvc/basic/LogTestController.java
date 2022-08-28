package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController 사용하면 view가 아니라, string이 그대로 반환됨
 * Slf4j 어노테이션으로, final 선언 필요없이 log 쓸 수 있음
 */
@Slf4j
@RestController
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " + name);

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.warn(" warn log={}", name);
        log.info(" info log={}", name);
        log.error("error log={}", name);
        return "ok";
    }
}
