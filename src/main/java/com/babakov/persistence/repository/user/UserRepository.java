package com.babakov.persistence.repository.user;

import com.babakov.persistence.entity.user.User;
import com.babakov.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository <U extends User> extends BaseRepository<U> {
    @Transactional
    U findByEmail(String email);

    boolean existsByEmail(String email);
}
