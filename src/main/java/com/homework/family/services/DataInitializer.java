package com.homework.family.services;



import com.homework.family.entities.MenuStructure;
import com.homework.family.entities.Role;
import com.homework.family.entities.User;
import com.homework.family.repository.MenuStructureRepository;
import com.homework.family.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final UserService userService;
    private final UserRepository userRepository;
    private final MenuStructureRepository menuStructureRepository;

    @Bean
    public CommandLineRunner createInitialData() {
        return args -> {
            User admin = User.builder()
                    .userName("admin")
                    .role(Role.LEADER)
                    .familyId("admins")
                    .build();
            userRepository.save(admin);
            List<String> icons = new ArrayList<>();
            icons.add("Game");
            icons.add("Contacts");
            icons.add("Browser");
            MenuStructure baseMenuStructure = MenuStructure.builder()
                    .backGround("Cars")
                    .theme("Dark")
                    .icons(icons)
                    .owner(admin)
                    .build();
            menuStructureRepository.saveAndFlush(baseMenuStructure);
            userService.addNewUser(java.util.Optional.of(admin),"Kovács Béla", "Kovács Family");
            userService.addNewUserToFamily(userRepository.findById("Kovács Béla"),"Kovács Lacika", Role.MEMBER);
            userService.addNewUserToFamily(userRepository.findById("Kovács Béla"), "Kovácsné Kalányos Dzseniferlopez", Role.MEMBER);
        };
    }
}