package com.os.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration       // 메모리로드
@EnableWebSecurity   // 활성화. 스프링시큐리티필터(SecurityConfig)가 스프링 필터체인에 등록이 된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean  // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();  // CSRF 공격방어기능 비활성화
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated()  // /user로 들어오면 인증이 필요하다
            .antMatchers("/manager/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")  // /manager/로 들어오면 인증뿐만아리라 권한도 필요하다
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()  // 이외의 요청은 모두 허용함
            .and()
            .formLogin()
            .loginPage("/loginForm");
    }
}
