package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.CaptchaResponseDto;
import com.massonus.flavourflow.dto.UserDto;
import com.massonus.flavourflow.entity.Role;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Value("${recaptcha.secret}")
    private String secret;

    private final RestTemplate restTemplate;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(RestTemplate restTemplate, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
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

    public UserDto createUser(final UserDto userDto) {

        userDto.setIsSameEmail(checkIsSameEmail(userDto));
        userDto.setIsSameUsername(checkIsSameUsername(userDto));

        if (userDto.getIsSameEmail() || userDto.getIsSameUsername()) {
            userDto.setIsSuccess(false);
            return userDto;
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRoles(Collections.singleton(userDto.getRole()));
        user.setPassword(userDto.getPassword());
        user.setRedactor(userDto.getRedactor());
        user.setBonuses(userDto.getBonuses());

        saveUser(user);

        userDto.setIsSuccess(true);
        return userDto;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public UserDto editUser(final UserDto userDto) {

        User editingUser = getUserById(userDto.getUserId());

        final User savedUser = getUserById(editingUser.getId());
        final Boolean isSameEmail = checkIsSameEmail(userDto);
        final Boolean isSameUsername = checkIsSameUsername(userDto);

        if (!userDto.getUsername().equals(editingUser.getUsername()) && isSameUsername) {
            userDto.setIsSameUsername(isSameUsername);
            return userDto;
        }

        if (!userDto.getEmail().equals(editingUser.getEmail()) && isSameEmail) {
            userDto.setIsSameEmail(isSameEmail);
            return userDto;
        }

        if (!userDto.getPassword().isEmpty()) {
            savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        savedUser.setUsername(userDto.getUsername());
        savedUser.setEmail(userDto.getEmail());
        savedUser.setRoles(Collections.singleton(userDto.getRole()));
        savedUser.setRedactor(userDto.getRedactor());
        savedUser.setBonuses(userDto.getBonuses());

        userDto.setIsSuccess(true);
        return userDto;
    }

    public UserDto editUser(final UserDto userDto, final User user) {
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
        user.setBonuses(0D);
        saveUser(user);

        userDto.setIsSuccessCaptcha(true);
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

    public Boolean checkCaptcha(final UserDto userDto) {
        String url = String.format(CAPTCHA_URL, secret, userDto.getCaptchaResponse());
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        return Objects.requireNonNull(response).isSuccess();
    }

    public User getUserById(Long id) {
        return userRepo.findUserById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll().stream().sorted(Comparator.comparing(User::getId)).toList();
    }

    public Double getUserBonuses(final Long userId) {
        return userRepo.findUserById(userId).getBonuses();
    }

    public Long deleteUser(Long id) {
        userRepo.delete(getUserById(id));
        return id;
    }


}
