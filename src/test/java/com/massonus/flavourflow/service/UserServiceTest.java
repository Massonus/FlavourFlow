package com.massonus.flavourflow.service;

import com.massonus.flavourflow.dto.UserDto;
import com.massonus.flavourflow.entity.Role;
import com.massonus.flavourflow.entity.User;
import com.massonus.flavourflow.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService target;

    private UserDto userDto;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setUsername("user1");
        expectedUser.setEmail("user@gmail.com");
        expectedUser.setPassword("password");

        userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setPassword("password");
        userDto.setOldPassword("password1");
        userDto.setEmail("user@gmail.com");
        userDto.setUserId(1L);
        userDto.setRole(Role.ADMIN);
    }

    @Test
    void shouldReturnRegistrationUser() {
        UserDto responseUserDto = target.registrationUser(userDto);
        Boolean isSuccess = responseUserDto.getIsSuccess();
        assertTrue(isSuccess);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepo, times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        Assertions.assertTrue(savedUser.getRoles().contains(Role.USER));
    }

    /*@Test
    void shouldEditUser() {
        when(userRepo.findUserById(userDto.getUserId())).thenReturn(expectedUser);

        UserDto responseUserDto = target.editUser(userDto);
        Boolean isSuccess = responseUserDto.getIsSuccess();
        assertTrue(isSuccess);
    }*/

    /*@Test
    void shouldCreateUser() {
        UserDto responseUserDto = target.createUser(userDto);
        assertTrue(responseUserDto.getIsSuccess());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepo, times(1)).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        Assertions.assertTrue(savedUser.getRoles().contains(Role.ADMIN));
    }*/

    @Test
    void shouldUpdateUser() {
        when(passwordEncoder.matches(userDto.getOldPassword(), expectedUser.getPassword())).thenReturn(true);
        when(userRepo.findUserById(expectedUser.getId())).thenReturn(expectedUser);
        UserDto responseUserDto = target.editUser(userDto, expectedUser);
        assertTrue(responseUserDto.getIsSuccess());
    }


}