package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        // getParameter의 응답 결과는 항상 string > 따라서 int로 변환해 줘야 함
        int age = Integer.parseInt(request.getParameter("age")   );

        // Member 객체에 두 개의 파라미터를 넣어줌
        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터를 보관한다, 파라미터 : string, object
        request.setAttribute("member", member);

        // 이렇게 되면, view가 넘어감
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
