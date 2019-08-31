package org.wrolp.scrim.data;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wrolp.scrim.data.entity.User;
import org.wrolp.scrim.data.repository.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public DatabaseLoader(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.err.println("Init Database!!!");
        User user = new User();
        user.setUsername("scrimadm");
        user.setPassword("scrim@11");
        user.setEmail("scrimadm@scrim.io");
        user.setCompany("Scrim CO. LT");
        user.setPhone("111-2300-25848-1");
        user.setRegisterDate(new Date());
        userRepository.save(user);
    }

}
