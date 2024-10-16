package org.poseidon.trading.service;

import org.poseidon.trading.domain.Trade;

import java.util.List;

public interface TradeService {

    Trade findById(int id);

    List<Trade> findAll();

    Trade add(Trade trade);

    Trade update(int id, Trade trade);

    void delete(int id);
}
