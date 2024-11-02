package com.purepoint.springbootpurepoint.user.repository;

import com.purepoint.springbootpurepoint.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
