package org.poseidon.trading.controller;

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
import org.poseidon.trading.service.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Spy
    private UserAssembler userAssembler;

    @InjectMocks
    private UserController userController;

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
    void home_shouldAddUsersToModelAndReturnViewName() {
        List<UserModel> users = Arrays.asList(userAssembler.toModel(user1), userAssembler.toModel(user2));
        when(userService.findAll()).thenReturn(users);
        String viewName = userController.home(model);
        verify(model).addAttribute("users", users);
        assertThat(viewName).isEqualTo("user/list");
    }

    @Test
    void addUser_shouldReturnViewName() {
        String viewName = userController.addUser(new User());
        assertThat(viewName).isEqualTo("user/add");
    }

    @Test
    void validate_shouldAddUserAndRedirectToListView_whenBindingResultHasNoErrors() {
        User user = user1;
        user.setPassword("password");

        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = userController.validate(user, bindingResult, model);

        verify(userService).add(user);
        assertThat(viewName).isEqualTo("user/list");
    }

    @Test
    void validate_shouldReturnAddView_whenBindingResultHasErrors() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = userController.validate(user, bindingResult, model);
        verify(userService, never()).add(user);
        assertThat(viewName).isEqualTo("user/add");
    }

    @Test
    void showUpdateForm_shouldAddUserToModelAndReturnViewName() {
        int id = 1;
        UserModel userModel = userAssembler.toModel(user1);

        when(userService.findById(id)).thenReturn(userModel);

        String viewName = userController.showUpdateForm(id, model);

        assertThat(viewName).isEqualTo("user/update");
    }

    @Test
    void updateUser_shouldUpdateUserAndRedirectToListView_whenBindingResultHasNoErrors() {
        User user = new User();
        user.setPassword("password");
        when(bindingResult.hasErrors()).thenReturn(false);
        String viewName = userController.updateUser(user, bindingResult, model);
        verify(userService).update(user);
        assertThat(viewName).isEqualTo("user/list");
    }

    @Test
    void updateUser_shouldReturnUpdateView_whenBindingResultHasErrors() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(true);
        String viewName = userController.updateUser(user, bindingResult, model);
        verify(userService, never()).update(user);
        assertThat(viewName).isEqualTo("user/update");
    }

    @Test
    void deleteUser_shouldDeleteUserAndRedirectToListView() {
        int id = 1;
        String viewName = userController.deleteUser(id, model);
        verify(userService).delete(id);
        assertThat(viewName).isEqualTo("user/list");
    }
}
