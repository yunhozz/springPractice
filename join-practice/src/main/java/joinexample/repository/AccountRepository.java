package joinexample.repository;

import joinexample.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Entity 에 의해 생성된 DB 에 접근하는 메소드 들을 사용하기 위한 인터페이스
 * CRUD(Create, Read, Update, Delete) 를 어떻게 할 것인지 정의
 * JpaRepository 에서 제공하는 여러 함수들을 이용.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
