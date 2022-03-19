package loginpractice.web.login;

import loginpractice.domain.login.LoginService;
import loginpractice.domain.member.Member;
import loginpractice.web.SessionConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * @ModelAttribute : 특정 메소드 또는 그 파라미터 매핑
     * @RequestParam : 1대 1 매핑
     * BindingResult : 오류 메세지를 저장하는 클래스
     */

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if(bindingResult.hasErrors()) return "/loginForm";

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getLoginPw());

        // 로그인 실패 시
        if(loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/loginForm";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession(); // true(기본값)을 넘길 경우 세션이 없으면 신규 세션 생성
                                                    // false 를 넘길 경우 세션이 없으면 null 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember); // 세션에 로그인 회원 정보 보관

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 세션 x -> null

        if(session != null) session.invalidate(); // 세션이 남아있는 경우 회원 정보에 대한 세션 날림

        return "redirect:/";
    }
}
