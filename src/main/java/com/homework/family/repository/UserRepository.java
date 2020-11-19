package com.homework.family.repository;

import com.homework.family.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String>{
}
