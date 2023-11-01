package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // 각 메소드에 붙일 수 있음
//transactional은 2개가 있음 스프링꺼랑 javax꺼가 있는데 이미 스프링 로직 디펜던시가 많이 들어가서 스프링꺼 쓰는게 좋다.
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    //여러가지 DI가 있음
    // 세터 - 장점은 테스트가 편하다. 하지만 누군가가 바꿀 수 있기 때문에 권장하지 않음
    // 생성자 인젝션 좋은 방법 굳 -> 생성자가 하나일 경우 @Autowired 필요없음
    // final + RequiredArgsContructor 이 베스트

    //회원가입
    public Long join (Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //exception
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회

    //Transactional(readOnly = true) 를 써서 성능을 조금 향상시킬 수 있음 ! 읽기전용에만 넣어놔야함ㅎ
    //기본적으로 클래스에 달아두고 나머지는 @Transactional 만 쓰기가 있는 곳에 달아 줌
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
