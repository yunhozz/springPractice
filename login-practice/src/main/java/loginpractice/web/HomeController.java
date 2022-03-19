package loginpractice.web;

import loginpractice.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 홈페이지 접속 시 미로그인이면 홈페이지를 보여주고 로그인이면 사용자의 이름을 보여줌
 */
@Controller
public class HomeController {

    /**
     * @SessionAttribute 를 사용하여 로그인 여부를 판단하는 세션 사용
     */
    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConstants.LOGIN_MEMBER, required = false) Member loginMember,
                       Model model) {

        // 세션에 회원 정보가 없으면
        if(loginMember == null) return "/home";

        // 있으면 회원 인스턴스를 model 에 담아 loginHome 뷰를 리턴
        model.addAttribute("member", loginMember);

        return "/loginHome";
    }

    /**
     * @SessionAttribute 를 사용하지 않는 경우
     */
/*
    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if(session == null) return "home";

        Member loginMember = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
        if(loginMember == null) return "home";

        model.addAttribute("member", loginMember);

        return "/loginHome";
    }
 */
}
