package simple.simplepractice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simple.simplepractice.domain.Member;
import simple.simplepractice.domain.MemberService;
import simple.simplepractice.domain.MemoryMemberRepository;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void join() {
        // given
        Member member = new Member();
        member.setName("yunho");

        // when
        Long saveId = memberService.join(member);
        Member findId = memberService.findOne(saveId).get();

        // then
        Assertions.assertThat(findId.getId()).isEqualTo(saveId);
    }

    @Test
    public void validateDuplicateMember() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho");
        member2.setName("yunho");

        // when
        memberService.join(member1);

        // then
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage().equals("이미 존재하는 회원입니다"));
        }
    }

    @Test
    public void findOne() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        memberService.join(member1);
        memberService.join(member2);
        boolean result1 = memberService.findOne(member1.getId()).isPresent();
        boolean result2 = memberService.findOne(member2.getId()).isPresent();

        // then
        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
    }

    @Test
    public void findMembers() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("yunho1");
        member2.setName("yunho2");

        // when
        memberService.join(member1);
        memberService.join(member2);
        int result = memberService.findMembers().size();

        // then
        Assertions.assertThat(result).isEqualTo(2);
    }
}
