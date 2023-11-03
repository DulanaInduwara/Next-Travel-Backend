package com.dulana.us.repo;

import com.dulana.us.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepo extends JpaRepository<UserAuth,String> {
}
