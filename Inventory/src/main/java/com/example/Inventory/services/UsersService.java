package com.example.Inventory.services;

import com.example.Inventory.models.Users;
import com.example.Inventory.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    public Optional<Users> findByUsername(String username) {
        return Optional.ofNullable(usersRepository.findByUsername(username));
    }
}
