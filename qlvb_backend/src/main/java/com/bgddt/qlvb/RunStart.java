package com.bgddt.qlvb;

import com.bgddt.qlvb.common.enums.Role;
import com.bgddt.qlvb.entities.Account;
import com.bgddt.qlvb.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RunStart implements CommandLineRunner {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Khi chương trình chạy
        // Insert vào csdl một user.
//        Account user = new Account();
//        user.setUsername("System");
//        user.setEmail("tgiangdev@gmail.com");
//        user.setActive(true);
//        user.setName("Trường Giang");
//        user.setRole(Role.SYSTEM);
//        user.setPassword(passwordEncoder.encode("123456"));
//        user.setCreatedBy("System");
//        accountRepository.save(user);
//        System.out.println(user);
    }
}
