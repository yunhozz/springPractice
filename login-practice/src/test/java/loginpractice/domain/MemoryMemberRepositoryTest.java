package loginpractice.domain;

import loginpractice.domain.member.Member;
import loginpractice.domain.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { repository.clearStore(); }

    @Test
    public void save() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        repository.save(member1);
        repository.save(member2);
        int result = repository.findAll().size();

        // then
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void findById() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        repository.save(member1);
        repository.save(member2);
        Member result1 = repository.findById(1L);
        Member result2 = repository.findById(2L);

        // then
        Assertions.assertThat(result1).isEqualTo(member1);
        Assertions.assertThat(result2).isEqualTo(member2);
    }

    @Test
    public void findByLoginId() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setLoginId("qkrdbsgh1121");
        member2.setLoginId("qkrdbsgh95");

        // when
        repository.save(member1);
        repository.save(member2);
        Member result = repository.findByLoginId("qkrdbsgh1121").get();

        // then
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        repository.save(member1);
        repository.save(member2);
        List<Member> result = repository.findAll();

        // then
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result).contains(member1);
        Assertions.assertThat(result).contains(member2);
    }
}
