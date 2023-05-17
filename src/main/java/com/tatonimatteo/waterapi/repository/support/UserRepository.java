package com.tatonimatteo.waterapi.repository.support;

import com.tatonimatteo.waterapi.entity.support.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional("supportTransactionManager")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
