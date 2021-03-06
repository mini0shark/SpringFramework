package example.springbootexam.service;

import example.springbootexam.domain.Member;
import example.springbootexam.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }
    @AfterEach
    void clear(){
        memoryMemberRepository.clearStore();
    }


    @Test
    void 회원가입() {
        // given
        Member member =new Member();
        member.setName("spring");
        // when
        Long saveId = memberService.join(member);
        // then

        Member findmember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findmember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 값입니다.");
        /*try{
            memberService.join(member2);
            fail("예외가 발생했습니다.");
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 값입니다.");
        }*/

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void find() {
    }
}