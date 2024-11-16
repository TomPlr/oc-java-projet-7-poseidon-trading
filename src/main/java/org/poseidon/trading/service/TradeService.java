package org.poseidon.trading.service;

import org.poseidon.trading.domain.Trade;

import java.util.List;

public interface TradeService {

    /**
     * Finds a Trade by its ID.
     *
     * @param id the ID of the Trade to find
     * @return the Trade with the given ID, or null if not found
     */
    Trade findById(int id);

    /**
     * Retrieves all Trade entities.
     *
     * @return a list of all Trade entities
     */
    List<Trade> findAll();

    /**
     * Adds a new Trade.
     *
     * @param trade the Trade to add
     * @return the added Trade
     */
    Trade add(Trade trade);

    /**
     * Updates an existing Trade with the given ID.
     *
     * @param id the ID of the Trade to update
     * @param trade the Trade with updated information
     * @return the updated Trade
     */
    Trade update(int id, Trade trade);

    /**
     * Deletes a Trade by its ID.
     *
     * @param id the ID of the Trade to delete
     */
    void delete(int id);
}
