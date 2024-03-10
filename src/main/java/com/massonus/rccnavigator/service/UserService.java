package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.UserDto;
import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User createUser(final UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRoles(Collections.singleton(Role.valueOf(userDto.getRole())));
        user.setPassword(userDto.getPassword());
        user.setRedactor(userDto.getRedactor());

        saveUser(user);

        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void editUser(User redactor, Long id, String username, String email, String password, Role role) {

        User savedUser = getUserById(id);
        savedUser.setUsername(username);

        if (!password.isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(password));
        }
        savedUser.setEmail(email);
        savedUser.setRoles(Collections.singleton(role));
        savedUser.setRedactor(redactor.getId());

    }

    public void updateUser(Long id, String password, String username, String email) {
        User savedUser = getUserById(id);

        if (!password.isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(password));
        }
        savedUser.setUsername(username);
        savedUser.setEmail(email);
    }

    public User getUserById(Long id) {
        return userRepo.findUserById(id);
    }

    public Set<User> getAllUsers() {
        return new HashSet<>(userRepo.findAll());
    }

    public void deleteUser(Long id) {
        userRepo.delete(getUserById(id));
    }


}
