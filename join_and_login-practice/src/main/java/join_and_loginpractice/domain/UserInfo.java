package join_and_loginpractice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity 에는 Setter X
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Long code;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;

    @Builder
    public UserInfo(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    /**
     * 사용자의 권한을 collection 형태로 반환. 단, 클래스 자료형은 GrantedAuthority 를 구현해야함.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }

    @Override
    public String getUsername() { return email; }

    @Override
    public String getPassword() { return password; }

    /**
     * 계정 만료 여부 반환
     */
    @Override
    public boolean isAccountNonExpired() { return true; }

    /**
     * 계정 잠금 여부 반환
     */
    @Override
    public boolean isAccountNonLocked() { return true; }

    /**
     * 패스워드의 만료 여부 반환
     */
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    /**
     * 계정 사용 가능 여부 반환
     */
    @Override
    public boolean isEnabled() { return true; }
}
