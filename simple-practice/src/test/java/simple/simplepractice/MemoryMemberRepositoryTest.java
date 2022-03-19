package simple.simplepractice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import simple.simplepractice.domain.Member;
import simple.simplepractice.domain.MemoryMemberRepository;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { memberRepository.clearStore(); }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("yunho");

        // when
        memberRepository.save(member);
        boolean result = memberRepository.findAll().isEmpty();

        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void findById() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setId(1L);
        member2.setId(2L);

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);
        boolean result1 = memberRepository.findById(1L).isPresent();
        boolean result2 = memberRepository.findById(2L).isPresent();

        // then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }

    @Test
    public void findByName() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);
        boolean result1 = memberRepository.findByName("yunho1").isPresent();
        boolean result2 = memberRepository.findByName("yunho2").isPresent();

        // then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);
        int result = memberRepository.findAll().size();

        // then
        Assertions.assertThat(result).isEqualTo(2);
    }
}
