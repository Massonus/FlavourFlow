package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.UserDto;
import com.massonus.rccnavigator.entity.Role;
import com.massonus.rccnavigator.entity.User;
import com.massonus.rccnavigator.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
        user.setRoles(Collections.singleton(userDto.getRole()));
        user.setPassword(userDto.getPassword());
        user.setRedactor(userDto.getRedactor());

        saveUser(user);

        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public User editUser(final UserDto userDto) {

        User savedUser = getUserById(userDto.getUserId());
        savedUser.setUsername(userDto.getUsername());

        if (!userDto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        savedUser.setEmail(userDto.getEmail());
        savedUser.setRoles(Collections.singleton(userDto.getRole()));
        savedUser.setRedactor(userDto.getRedactor());

        return savedUser;
    }

    public UserDto updateUser(final UserDto userDto, final User user) {
        final User savedUser = getUserById(user.getId());
        final Boolean isSameEmail = checkIsSameEmail(userDto);
        final Boolean isSameUsername = checkIsSameUsername(userDto);

        if (!passwordEncoder.matches(userDto.getOldPassword(), savedUser.getPassword())) {
            userDto.setIsIncorrectOldPassword(true);
            return userDto;
        }

        if (!userDto.getUsername().equals(user.getUsername()) && isSameUsername) {
            userDto.setIsSameUsername(isSameUsername);
            return userDto;
        }

        if (!userDto.getEmail().equals(user.getEmail()) && isSameEmail) {
            userDto.setIsSameEmail(isSameEmail);
            return userDto;
        }

        if (!userDto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        savedUser.setUsername(userDto.getUsername());
        savedUser.setEmail(userDto.getEmail());

        userDto.setIsSuccess(true);
        return userDto;
    }

    public UserDto registrationUser(final UserDto userDto) {

        userDto.setIsSameEmail(checkIsSameEmail(userDto));
        userDto.setIsSameUsername(checkIsSameUsername(userDto));

        if (userDto.getIsSameEmail() || userDto.getIsSameUsername()) {
            userDto.setIsSuccess(false);
            return userDto;
        }

        final User user = new User();
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRoles(Collections.singleton(Role.USER));
        user.setRedactor("registration");
        saveUser(user);

        userDto.setIsSuccess(true);
        return userDto;
    }

    private Boolean checkIsSameUsername(final UserDto userDto) {
        return getAllUsers().stream()
                .anyMatch(u -> u.getUsername().equals(userDto.getUsername()));
    }

    private Boolean checkIsSameEmail(final UserDto userDto) {
        return getAllUsers().stream()
                .anyMatch(u -> u.getEmail().equals(userDto.getEmail()));
    }

    public User getUserById(Long id) {
        return userRepo.findUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll().stream().sorted(Comparator.comparing(User::getId)).toList();
    }

    public Long deleteUser(Long id) {
        userRepo.delete(getUserById(id));
        return id;
    }


}
