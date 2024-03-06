package com.os.security1.repository;

import com.os.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// CRUD 함수를 JpaRepository 가 들고 있음
// @Repository 라는 어노테이션이 없어도 IoC되요. 이유는 JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {
}
