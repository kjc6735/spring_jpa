package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final MemberRepository memberRepository;

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello");
        return "hello";
    }

//    @GetMapping("test")
//    @Transactional
//    public String test(){
//        Member member1 = new Member();
//        member1.setName("kim");
//        memberRepository.save(member1);
//
//
//        return "hello";
//    }
}
