package org.poseidon.trading.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.poseidon.trading.domain.Trade;
import org.poseidon.trading.repositories.TradeRepository;
import org.poseidon.trading.service.TradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    TradeRepository tradeRepository;

    public TradeServiceImpl(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public Trade findById(int id) {
        return tradeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public Trade add(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Override
    public Trade update(int id, Trade trade) {
        Trade updatedTrade = tradeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(trade.getAccount()).ifPresent(updatedTrade::setAccount);
        Optional.ofNullable(trade.getType()).ifPresent(updatedTrade::setType);
        Optional.ofNullable(trade.getBuyQuantity()).ifPresent(updatedTrade::setBuyQuantity);

        return tradeRepository.save(updatedTrade);
    }

    @Override
    public void delete(int id) {
        tradeRepository.deleteById(id);
    }
}
