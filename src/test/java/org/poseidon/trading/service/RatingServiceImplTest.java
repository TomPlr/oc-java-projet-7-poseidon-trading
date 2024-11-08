package org.poseidon.trading.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.Rating;
import org.poseidon.trading.repositories.RatingRepository;
import org.poseidon.trading.service.impl.RatingServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Test
    void findById_shouldReturnRating_whenRatingExists() {
        int id = 1;
        Rating rating = new Rating();

        when(ratingRepository.findById(id)).thenReturn(Optional.of(rating));

        Rating result = ratingService.findById(id);

        assertThat(result).isEqualTo(rating);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenRatingDoesNotExist() {
        int id = 1;

        when(ratingRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllRatings() {
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());

        when(ratingRepository.findAll()).thenReturn(ratings);

        List<Rating> result = ratingService.findAll();

        assertThat(result).isEqualTo(ratings);
    }

    @Test
    void add_shouldSaveRating() {
        Rating rating = new Rating();

        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.add(rating);

        assertThat(result).isEqualTo(rating);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void update_shouldUpdateRating_whenRatingExists() {
        int id = 1;
        Rating existingRating = new Rating();
        Rating updatedRating = new Rating();
        updatedRating.setMoodysRating("Aaa");
        updatedRating.setSandPRating("AAA");
        updatedRating.setFitchRating("AAA");
        updatedRating.setOrderNumber(1);

        when(ratingRepository.findById(id)).thenReturn(Optional.of(existingRating));
        when(ratingRepository.save(existingRating)).thenReturn(updatedRating);

        Rating result = ratingService.update(id, updatedRating);

        assertThat(result).isEqualTo(updatedRating);
        assertThat(existingRating.getMoodysRating()).isEqualTo(updatedRating.getMoodysRating());
        assertThat(existingRating.getSandPRating()).isEqualTo(updatedRating.getSandPRating());
        assertThat(existingRating.getFitchRating()).isEqualTo(updatedRating.getFitchRating());
        assertThat(existingRating.getOrderNumber()).isEqualTo(updatedRating.getOrderNumber());
        verify(ratingRepository, times(1)).save(existingRating);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenRatingDoesNotExist() {
        int id = 1;
        Rating updatedRating = new Rating();

        when(ratingRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.update(id, updatedRating))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteRating() {
        int id = 1;

        ratingService.delete(id);

        verify(ratingRepository, times(1)).deleteById(id);
    }
}
