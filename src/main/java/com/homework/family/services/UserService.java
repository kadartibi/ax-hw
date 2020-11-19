package com.homework.family.services;

import com.homework.family.entities.MenuStructure;
import com.homework.family.entities.Role;
import com.homework.family.entities.User;
import com.homework.family.repository.MenuStructureRepository;
import com.homework.family.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MenuStructureRepository menuStructureRepository;
    public void addNewUser(Optional<User> admin, String name, String family) throws Exception {
        if(admin.isPresent()){
            if(admin.get().getRoles().contains(Role.LEADER)) {
                User newUser =
                        User.builder()
                                .userName(name)
                                .familyId(family)
                                .role(Role.LEADER)
                                .build();
                userRepository.save(newUser);
                MenuStructure menuStructure = MenuStructure.builder().owner(newUser).backGround("Cars").theme("Dark").build();
                menuStructureRepository.saveAndFlush(menuStructure);
                newUser.setMenuStructure(menuStructure);
                userRepository.save(newUser);

            } else throw new Exception("You have no rights to add a new member");
        }
    }

    public void addNewUserToFamily(Optional<User> leader, String name,Role role) throws Exception {
        if (leader.isPresent()) {
            if (leader.get().getRoles().contains(Role.LEADER)) {
                User newUser =
                        User.builder()
                                .userName(name)
                                .familyId(leader.get().getFamilyId())
                                .role(Role.MEMBER)
                                .build();
                userRepository.save(newUser);
                MenuStructure menuStructure = MenuStructure.builder().owner(newUser).backGround("Cars").theme("Dark").build();
                menuStructureRepository.saveAndFlush(menuStructure);
                newUser.setMenuStructure(menuStructure);
                userRepository.save(newUser);

            } else throw new Exception("You have no rights to add a new member");
        }
    }
}
