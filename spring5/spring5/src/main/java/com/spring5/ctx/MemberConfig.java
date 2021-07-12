package com.spring5.ctx;

import com.spring5.repository.MemberRepository;
import com.spring5.service.ChangePasswordService;
import com.spring5.service.MemberRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
    final private MemberRepository memberRepository;

    public MemberConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberRepository);
    }
    @Bean
    public ChangePasswordService changePasswordService(){
        return new ChangePasswordService(memberRepository);
    }
}
