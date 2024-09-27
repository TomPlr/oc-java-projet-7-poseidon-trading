package org.poseidon.trading.repositories;


import org.poseidon.trading.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
