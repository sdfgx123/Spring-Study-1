package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    // 여기서 서블릿 한번만 호출해 주면 됨
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


}
