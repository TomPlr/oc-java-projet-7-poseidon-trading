package org.poseidon.trading.service;

import org.poseidon.trading.domain.Rating;

import java.util.List;

public interface RatingService {

    /**
     * Finds a Rating by its ID.
     *
     * @param id the ID of the Rating to find
     * @return the Rating with the given ID, or null if not found
     */
    Rating findById(int id);

    /**
     * Retrieves all Rating entities.
     *
     * @return a list of all Rating entities
     */
    List<Rating> findAll();

    /**
     * Adds a new Rating.
     *
     * @param rating the Rating to add
     * @return the added Rating
     */
    Rating add(Rating rating);

    /**
     * Updates an existing Rating with the given ID.
     *
     * @param id     the ID of the Rating to update
     * @param rating the Rating with updated information
     * @return the updated Rating
     */
    Rating update(int id, Rating rating);

    /**
     * Deletes a Rating by its ID.
     *
     * @param id the ID of the Rating to delete
     */
    void delete(int id);
}
