package springeighthproject.spring_jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import springeighthproject.spring_jpa.domain.Member;
import springeighthproject.spring_jpa.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Yoon Hyun sik");
        //when
        Long savedId = memberService.join(member);
        //then
//        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Yoon");

        Member member2 = new Member();
        member2.setName("Yoon");
        //when
        memberService.join(member1);
        memberService.join(member2); // 예외발생
        //then
        fail("예외가 발생해야 한다.");


    }

}