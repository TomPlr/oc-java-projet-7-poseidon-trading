package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.User;
import org.poseidon.trading.repositories.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginController loginController;

    @Test
    void login_shouldReturnModelAndViewWithViewNameLogin() {
        ModelAndView mav = loginController.login();
        assertThat(mav.getViewName()).isEqualTo("login");
    }

    @Test
    void getAllUserArticles_shouldReturnModelAndViewWithUsersAndViewNameUserList() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        ModelAndView mav = loginController.getAllUserArticles();
        assertThat(mav.getModel().get("users")).isEqualTo(users);
        assertThat(mav.getViewName()).isEqualTo("user/list");
    }

    @Test
    void error_shouldReturnModelAndViewWithErrorMessageAndViewName403() {
        ModelAndView mav = loginController.error();
        assertThat(mav.getModel().get("errorMsg")).isEqualTo("You are not authorized for the requested data.");
        assertThat(mav.getViewName()).isEqualTo("403");
    }
}
