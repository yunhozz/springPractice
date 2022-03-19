package join_and_loginpractice.repository;

import join_and_loginpractice.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email); // email 을 통해 회원을 조회하기 위해 추가
}
