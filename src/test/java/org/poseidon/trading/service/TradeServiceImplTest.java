package org.poseidon.trading.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.Trade;
import org.poseidon.trading.repositories.TradeRepository;
import org.poseidon.trading.service.impl.TradeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeServiceImpl tradeService;

    @Test
    void findById_shouldReturnTrade_whenTradeExists() {
        int id = 1;
        Trade trade = new Trade();
        when(tradeRepository.findById(id)).thenReturn(Optional.of(trade));
        Trade result = tradeService.findById(id);
        assertThat(result).isEqualTo(trade);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenTradeDoesNotExist() {
        int id = 1;
        when(tradeRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> tradeService.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllTrades() {
        List<Trade> trades = Arrays.asList(new Trade(), new Trade());
        when(tradeRepository.findAll()).thenReturn(trades);
        List<Trade> result = tradeService.findAll();
        assertThat(result).isEqualTo(trades);
    }

    @Test
    void add_shouldSaveTrade() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);
        Trade result = tradeService.add(trade);
        assertThat(result).isEqualTo(trade);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void update_shouldUpdateTrade_whenTradeExists() {
        int id = 1;
        Trade existingTrade = new Trade();
        Trade updatedTrade = new Trade();
        updatedTrade.setAccount("New Account");
        updatedTrade.setType("New Type");
        updatedTrade.setBuyQuantity(100d);

        when(tradeRepository.findById(id)).thenReturn(Optional.of(existingTrade));
        when(tradeRepository.save(existingTrade)).thenReturn(updatedTrade);

        Trade result = tradeService.update(id, updatedTrade);

        assertThat(result).isEqualTo(updatedTrade);
        assertThat(existingTrade.getAccount()).isEqualTo(updatedTrade.getAccount());
        assertThat(existingTrade.getType()).isEqualTo(updatedTrade.getType());
        assertThat(existingTrade.getBuyQuantity()).isEqualTo(updatedTrade.getBuyQuantity());
        verify(tradeRepository, times(1)).save(existingTrade);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenTradeDoesNotExist() {
        int id = 1;
        Trade updatedTrade = new Trade();
        when(tradeRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> tradeService.update(id, updatedTrade))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteTrade() {
        int id = 1;
        tradeService.delete(id);
        verify(tradeRepository, times(1)).deleteById(id);
    }
}
