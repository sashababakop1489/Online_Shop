package com.babakov;

import com.babakov.persistence.repository.user.AdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class OnlineShopApplication {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;

    public OnlineShopApplication(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder, BCryptPasswordEncoder encoder) {
        this.adminRepository = adminRepository;
        this.encoder = encoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineShopApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
//        Admin admin = new Admin();
//        admin.setEmail("admin@mail.com");
//        admin.setPassword(encoder.encode("rootroot"));
//        adminRepository.save(admin);
    }
}
