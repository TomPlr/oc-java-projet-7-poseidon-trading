package org.poseidon.trading.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.poseidon.trading.domain.Rating;
import org.poseidon.trading.repositories.RatingRepository;
import org.poseidon.trading.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    RatingRepository ratingRepository;

    public RatingServiceImpl(final RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating findById(int id) {
        return ratingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating update(int id, Rating rating) {
        Rating updatedRating = ratingRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(rating.getMoodysRating()).ifPresent(updatedRating::setMoodysRating);
        Optional.ofNullable(rating.getSandPRating()).ifPresent(updatedRating::setSandPRating);
        Optional.ofNullable(rating.getFitchRating()).ifPresent(updatedRating::setFitchRating);
        Optional.ofNullable(rating.getOrderNumber()).ifPresent(updatedRating::setOrderNumber);

        return ratingRepository.save(updatedRating);
    }

    @Override
    public void delete(int id) {
        ratingRepository.deleteById(id);
    }
}
