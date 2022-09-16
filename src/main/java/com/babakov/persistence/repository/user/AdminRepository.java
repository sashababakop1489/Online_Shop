package com.babakov.persistence.repository.user;

import com.babakov.persistence.entity.user.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin>{
}
