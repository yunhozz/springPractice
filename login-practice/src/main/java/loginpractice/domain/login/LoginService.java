package loginpractice.domain.login;

import loginpractice.domain.member.Member;
import loginpractice.domain.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // DI 역할을 해줌
public class LoginService {

    private final MemoryMemberRepository memberRepository;

    /*
    LoginService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    */

    /**
     * return = null 이면 로그인 실패
     */
    public Member login(String loginId, String loginPw) {
        return memberRepository.findByLoginId(loginId)
                                .filter(member -> member.getLoginPw().equals(loginPw))
                                .orElse(null);
    }
}
