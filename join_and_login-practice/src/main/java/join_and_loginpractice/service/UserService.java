package join_and_loginpractice.service;

import join_and_loginpractice.domain.UserInfo;
import join_and_loginpractice.dto.UserInfoDto;
import join_and_loginpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * 입력받은 패스워드를 BCrypt 로 암호화한 후 회원을 저장해주는 메소드
     * @param : infoDto (회원정보가 들어있는 DTO)
     * @return : 저장되는 회원의 PK
     */
    public Long join(UserInfoDto userInfoDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userInfoDto.setPassword(encoder.encode(userInfoDto.getPassword()));

        UserInfo information = UserInfo.builder()
                                        .email(userInfoDto.getEmail())
                                        .password(userInfoDto.getPassword())
                                        .auth(userInfoDto.getAuth())
                                        .build();

        return userRepository.save(information).getCode();
    }

    /**
     * Spring Security 필수 메소드 구현
     * @param : email
     * @return : UserDetails (UserInfo 가 상속 받아 자동 다운캐스팅)
     * @throws : UsernameNotFoundException (유저가 없을 때 예외 발생)
     */
    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
