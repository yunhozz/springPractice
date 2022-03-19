package join_and_loginpractice.config;

import join_and_loginpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // Spring Security 를 활성화
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    /**
     * 인증을 무시할 경로들을 설정하는 곳
     * static 하위 폴더(css, js, img)는 무조건 접근이 가능해야 하기 때문에 인증 무시
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "js/**", "/img/**", "/h2-console/**");
    }

    /**
     * http 관련 인증 설정하는 곳
     * 1. 접근에 대한 인증 설정
     * 2. 로그인에 대한 설정
     * 3. 로그아웃에 대한 설정
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
                .antMatchers("/").hasRole("USER") // USER, ADMIN 둘다 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN 만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관없이 권한이 있어야 접근 가능

                .and()

                .formLogin()
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 redirect 주소

                .and()

                .logout()
                .logoutSuccessUrl("/login") // 로그아웃 성공 시 redirect 주소
                .invalidateHttpSession(true); // 세션 날리기
    }

    /**
     * 로그인할 때 필요한 정보를 가져오는 곳
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder()); // 패스워드 인코더 사용
    }
}