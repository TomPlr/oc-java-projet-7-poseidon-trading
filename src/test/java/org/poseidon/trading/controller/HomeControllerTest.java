package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @Test
    void home_shouldReturnViewName() {
        String viewName = homeController.home(model);
        assertThat(viewName).isEqualTo("home");
    }

    @Test
    void adminHome_shouldReturnRedirectViewName() {
        String viewName = homeController.adminHome(model);
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
    }
}
