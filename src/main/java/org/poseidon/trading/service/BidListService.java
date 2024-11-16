package org.poseidon.trading.service;

import org.poseidon.trading.domain.BidList;

import java.util.List;

public interface BidListService {


    /**
     * Finds a BidList by its ID.
     *
     * @param id the ID of the BidList to find
     * @return the BidList with the given ID, or null if not found
     */
    BidList findById(int id);

    /**
     * Retrieves all BidList entities.
     *
     * @return a list of all BidList entities
     */
    List<BidList> findAll();

    /**
     * Adds a new BidList.
     *
     * @param bidList the BidList to add
     * @return the added BidList
     */
    BidList add(BidList bidList);

    /**
     * Updates an existing BidList with the given ID.
     *
     * @param id      the ID of the BidList to update
     * @param bidList the BidList with updated information
     * @return the updated BidList
     */
    BidList update(int id, BidList bidList);

    /**
     * Deletes a BidList by its ID.
     *
     * @param id the ID of the BidList to delete
     */
    void delete(int id);
}
