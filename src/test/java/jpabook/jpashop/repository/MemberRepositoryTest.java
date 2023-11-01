package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional // 기본적으로 test에서의 transactional은 인서트 쿼리 안보내고 롤백시킨다. 그렇기 때문에 Insert까지 보기 위해서는 Rollback(false)를 써주면 된다. 아니면 flush()함수를 사용한다.
class MemberRepositoryTest {
    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        }catch (IllegalStateException e){
            return ;
        }

        //then
        fail("예외가 발생해야 한다.");
    }

    //junit4
//    @Test(expected=IllegalStateException.class)
//    public void 중복_회원_예외() throws Exception {
//        //given
//        Member member1 = new Member();
//        member1.setName("kim");
//
//        Member member2 = new Member();
//        member2.setName("kim");
//        //when
//        memberService.join(member1);
//        memberService.join(member2);// 예외가 발생해야 한다.
//
//        //then
//        fail("예외가 발생해야 한다.");
//    }

    @Test
    public void 중복_회원_예외2() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}