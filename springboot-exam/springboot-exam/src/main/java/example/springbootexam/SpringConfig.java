package example.springbootexam;

import example.springbootexam.repository.JdbcMemberRepository;
import example.springbootexam.repository.MemberRepository;
import example.springbootexam.repository.MemoryMemberRepository;
import example.springbootexam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.*;

@Configuration
public class SpringConfig {
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
    }
}
