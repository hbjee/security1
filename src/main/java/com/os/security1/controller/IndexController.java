package com.os.security1.controller;

import com.os.security1.model.User;
import com.os.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // View를 리턴하겠다.
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // localhost:8080/
    // localhost:8080
    @GetMapping({ "", "/" })
    public String index() {
        // 머스테치 기본폴더 src/main/resource/
        // 뷰리졸버 설정: templates(prefix), .mustache(suffix) 생략가능!!
        return "index";  // src/main/resource/templates/index.mustache
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    // 스프링시큐리티가 해당주소를 낚아채버리네요!!  --> SecurityConfig파일작성후 안낚아챔
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }
    @PostMapping("/join")
    public  String join(User user) {
        System.out.println("user = " + user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);  // 회원가입 잘됨. 비밀번호:1234 =>시큐리티로 로그인할 수 없음. 이유는 패스워드가 암호화 안되었기 때문
        return "redirect:loginForm";
    }

}
