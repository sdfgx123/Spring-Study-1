package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
// 생성자에 Autowired 자동으로 들어가면서 의존성 주입
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // object provider를 통해 의존성을 주입받음
    // provider를 쓰면, 고객 request가 들어온 상태에서, http가 살아있으니까, scope를 쓸 수 있다
    private final MyLogger myLogger;

    // ResponseBody : 뷰 화면 없이 문자 그대로 반환시 사용
    @RequestMapping("log-demo")
    @ResponseBody
    // 자바에서 제공하는 표준 서블릿 규약 > 이를 통해 고객의 request 정보를 받을 수 있음
    public String logDemo(HttpServletRequest request) {
        // 고객이 어떤 URL로 요청했는지 알 수 있음
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
