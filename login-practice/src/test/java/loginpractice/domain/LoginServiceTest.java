package loginpractice.domain;

import loginpractice.domain.login.LoginService;
import loginpractice.domain.member.Member;
import loginpractice.domain.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginServiceTest {

    LoginService loginService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        loginService = new LoginService(memberRepository);
    }

    @AfterEach
    public void afterEach () { memberRepository.clearStore(); }

    @Test
    public void login() {
        // given
        Member member = new Member();
        member.setLoginId("test123");
        member.setLoginPw("Test123!");

        // when
        memberRepository.save(member);
        Member result1 = loginService.login("test123", "Test123!");
        Member result2 = loginService.login("test123", "Test");

        // then
        Assertions.assertThat(result1).isEqualTo(member);
        Assertions.assertThat(result2).isNull();
    }
}
