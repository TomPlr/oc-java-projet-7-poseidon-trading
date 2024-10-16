package org.poseidon.trading.service;

import org.poseidon.trading.domain.Rating;

import java.util.List;

public interface RatingService {

    Rating findById(int id);

    List<Rating> findAll();

    Rating add(Rating rating);

    Rating update(int id, Rating rating);

    void delete(int id);
}
