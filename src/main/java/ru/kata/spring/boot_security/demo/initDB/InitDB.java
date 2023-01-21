package ru.kata.spring.boot_security.demo.initDB;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDB {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public InitDB(UserService userService, RoleService roleService, UserRepository userRepository, RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initDBInit() {
        User user = new User("vano@gmail.com", "Ivan", "Ivanov", 50, new BCryptPasswordEncoder().encode("1234"));
        Role role2 = roleRepository.save(new Role("ROLE_USER"));
        Set<Role> roleUser = new HashSet<>(Set.of(role2));
        user.setRoles(roleUser);
        userRepository.save(user);

        User admin = new User("kris@gmail.com", "Kris", "Jones", 30, new BCryptPasswordEncoder().encode("1234"));
        Role role1 = roleRepository.save(new Role("ROLE_ADMIN"));
        Set<Role> roleAdmin = new HashSet<>(Set.of(role1));
        admin.setRoles(roleAdmin);
        userRepository.save(admin);


    }

}
