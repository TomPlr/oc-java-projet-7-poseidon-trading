package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.Trade;
import org.poseidon.trading.service.impl.TradeServiceImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeControllerTest {

    @Mock
    private TradeServiceImpl tradeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TradeController tradeController;

    @Test
    void home_shouldAddTradesToModelAndReturnViewName() {
        List<Trade> trades = Arrays.asList(new Trade(), new Trade());
        when(tradeService.findAll()).thenReturn(trades);
        String viewName = tradeController.home(model);
        verify(model).addAttribute("trades", trades);
        assertThat(viewName).isEqualTo("trade/list");
    }

    @Test
    void addUser_shouldReturnViewName() {
        String viewName = tradeController.addUser(new Trade());
        assertThat(viewName).isEqualTo("trade/add");
    }

    @Test
    void validate_shouldAddTradeAndReturnViewName() {
        Trade trade = new Trade();
        String viewName = tradeController.validate(trade, bindingResult, model);
        verify(tradeService).add(trade);
        assertThat(viewName).isEqualTo("trade/add");
    }

    @Test
    void showUpdateForm_shouldAddTradeToModelAndReturnViewName() {
        int id = 1;
        Trade trade = new Trade();
        when(tradeService.findById(id)).thenReturn(trade);
        String viewName = tradeController.showUpdateForm(id, model);
        verify(model).addAttribute("trade", trade);
        assertThat(viewName).isEqualTo("trade/update");
    }

    @Test
    void updateTrade_shouldUpdateTradeAndRedirectToListView() {
        int id = 1;
        Trade trade = new Trade();
        String viewName = tradeController.updateTrade(id, trade, bindingResult, model);
        verify(model).addAttribute("trade", trade);
        assertThat(viewName).isEqualTo("redirect:/trade/list");
    }

    @Test
    void deleteTrade_shouldDeleteTradeAndRedirectToListView() {
        int id = 1;
        String viewName = tradeController.deleteTrade(id, model);
        verify(tradeService).delete(id);
        assertThat(viewName).isEqualTo("redirect:/trade/list");
    }
}
