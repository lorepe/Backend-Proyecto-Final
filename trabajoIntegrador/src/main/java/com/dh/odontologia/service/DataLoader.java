package com.dh.odontologia.service;

import com.dh.odontologia.model.AppUser;
import com.dh.odontologia.model.AppUserRole;
import com.dh.odontologia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");
        userRepository.save(new AppUser("Diego", "lorena", "lorena@digital.com", hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("Paula", "gloria", "gloria@digital.com", hashedPassword2, AppUserRole.USER));
    }
}
