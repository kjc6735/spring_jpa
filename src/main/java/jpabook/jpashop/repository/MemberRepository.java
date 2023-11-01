package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
//      1번
//    @PersistenceContext
//    private EntityManager em; // 이 친구도 생성자 인젝션 가능하다.
//

    //2 번
//    @Autowired
//    private EntityManager em;
//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    //3 + RequiredArgsConstructor
    private final EntityManager em;


    //factory 주입
    /*
       @PersistenceUnit
       private EntityManagerFactory emf;
    * */
    public void save (Member member){
        // 영속성 컨텍스트에 넣음
        em.persist(member);
    }

    public Member findOne(Long id){
        // jpa의 단건조회 , 두번째는 pk 넣어줘야함
        return em.find(Member.class, id);
    }

    //jpql
    //대상이 객체임 !!!!! Member <<
    public List<Member> findAll(){
        return  em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }
}
