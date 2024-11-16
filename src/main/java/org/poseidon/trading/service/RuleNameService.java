package org.poseidon.trading.service;

import org.poseidon.trading.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    /**
     * Finds a RuleName by its ID.
     *
     * @param id the ID of the RuleName to find
     * @return the RuleName with the given ID, or null if not found
     */
    RuleName findById(int id);

    /**
     * Retrieves all RuleName entities.
     *
     * @return a list of all RuleName entities
     */
    List<RuleName> findAll();

    /**
     * Adds a new RuleName.
     *
     * @param ruleName the RuleName to add
     * @return the added RuleName
     */
    RuleName add(RuleName ruleName);

    /**
     * Updates an existing RuleName with the given ID.
     *
     * @param id       the ID of the RuleName to update
     * @param ruleName the RuleName with updated information
     * @return the updated RuleName
     */
    RuleName update(int id, RuleName ruleName);

    /**
     * Deletes a RuleName by its ID.
     *
     * @param id the ID of the RuleName to delete
     */
    void delete(int id);
}
