package org.poseidon.trading.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.assembler.UserAssembler;
import org.poseidon.trading.domain.User;
import org.poseidon.trading.model.UserModel;
import org.poseidon.trading.repositories.UserRepository;
import org.poseidon.trading.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserAssembler userAssembler;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setPassword("password1");
        user1.setFullname("User One");
        user1.setRole("USER");

        user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setPassword("password2");
        user2.setFullname("User Two");
        user2.setRole("ADMIN");
    }

    @Test
    void findById_shouldReturnUserModel_whenUserExists() {

        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        UserModel result = userService.findById(user1.getId());

        assertThat(result).isEqualTo(userAssembler.toModel(user1));
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenUserDoesNotExist() {
        int id = 1;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllUserModels() {
        List<User> users = Arrays.asList(user1, user2);
        List<UserModel> userModels = Arrays.asList(userAssembler.toModel(user1), userAssembler.toModel(user2));

        when(userRepository.findAll()).thenReturn(users);

        List<UserModel> result = userService.findAll();

        assertThat(result).isEqualTo(userModels);
    }

    @Test
    void add_shouldSaveUserAndReturnUserModel() {

        when(userRepository.save(user1)).thenReturn(user1);

        UserModel result = userService.add(user1);

        assertThat(result).isEqualTo(userAssembler.toModel(user1));
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    void update_shouldUpdateUserAndReturnUserModel_whenUserExists() {
        User updatedUser = user1;
        updatedUser.setUsername("New Username");
        updatedUser.setFullname("New Fullname");
        updatedUser.setPassword("New Password");


        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        UserModel result = userService.update(updatedUser);

        assertThat(result).isEqualTo(userAssembler.toModel(updatedUser));
        assertThat(updatedUser.getUsername()).isEqualTo(result.username());
        assertThat(updatedUser.getFullname()).isEqualTo(result.fullname());
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenUserDoesNotExist() {
        User updatedUser = user1;

        when(userRepository.findById(user1.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.update(updatedUser))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteUser() {
        int id = 1;

        userService.delete(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}
