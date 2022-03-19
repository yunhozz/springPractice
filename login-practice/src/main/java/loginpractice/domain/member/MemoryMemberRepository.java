package loginpractice.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j // 자바 오픈소스 로깅 프레임워크
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>(); // Hashmap 을 thread-safe 할 수 있도록 함
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member{}", member); // member 정보에 대한 로그를 저장
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return this.findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() { store.clear(); }

    /**
     * 테스트용 회원 가입 데이터
     */
    @PostConstruct
    public void init() {
        Member member = new Member();

        member.setLoginId("test");
        member.setLoginPw("Test!");
        member.setName("테스터");

        save(member);
    }
}