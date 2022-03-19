package join_and_loginpractice.controller;

import join_and_loginpractice.domain.UserInfo;
import join_and_loginpractice.dto.UserInfoDto;
import join_and_loginpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute UserInfoDto userInfoDto) { return "signupForm"; }

    @PostMapping("/signup")
    public String signup(UserInfoDto userInfoDto) {
        userService.join(userInfoDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute UserInfoDto userInfoDto) { return "loginForm"; }

    @PostMapping("/login")
    public String login(UserInfoDto userInfoDto) {
        UserInfo userInfo = userService.loadUserByUsername(userInfoDto.getEmail());
        if(userInfo == null) return "loginForm";

        return "redirect:/";
    }

    @GetMapping("/")
    public String userPage() { return "user"; }

    @GetMapping("/admin")
    public String adminPage() { return "admin"; }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler()
                .logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }
}
