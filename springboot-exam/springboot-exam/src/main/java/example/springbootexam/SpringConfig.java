package example.springbootexam;

import example.springbootexam.repository.MemberRepository;
import example.springbootexam.repository.MemoryMemberRepository;
import example.springbootexam.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
