package joinexample.dto;

import joinexample.domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * DTO(Data Transfer Object) : 웹 서비스의 클라이언트와 서비스 계층 사이에서 교환되는 데이터를 담는 그릇
 *
 * <Entity 클래스와 분리해서 사용하는 이유>
 * -> 관심사의 분리 : 핵심 로직을 담는 도메인 영역의 일부 vs 데이터를 전달하는 그릇
 * -> Validation 로직 및 불필요한 코드 등과의 분리 : JPA 어노테이션과 유효성 검증 어노테이션의 혼용 방지
 * -> API 스펙과의 분리 : 스펙의 변경이 전파되는 것을 방지
 * -> API 스펙의 파악이 용이
 */
@Data
@NoArgsConstructor
public class AccountForm {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    public Account toEntity() {
        return Account.builder()
                        .id(id)
                        .username(username)
                        .password(new BCryptPasswordEncoder().encode(password)) // 암호화
                        .email(email)
                        .age(age)
                        .role(role)
                        .build();
    }
}
