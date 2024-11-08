package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.Rating;
import org.poseidon.trading.service.impl.RatingServiceImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @Mock
    private RatingServiceImpl ratingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RatingController ratingController;

    @Test
    void home_shouldAddRatingsToModelAndReturnViewName() {
        List<Rating> ratings = Arrays.asList(new Rating(), new Rating());
        when(ratingService.findAll()).thenReturn(ratings);
        String viewName = ratingController.home(model);
        verify(model).addAttribute("ratings", ratings);
        assertThat(viewName).isEqualTo("rating/list");
    }

    @Test
    void addRatingForm_shouldReturnViewName() {
        String viewName = ratingController.addRatingForm(new Rating());
        assertThat(viewName).isEqualTo("rating/add");
    }

    @Test
    void validate_shouldAddRatingAndReturnViewName() {
        Rating rating = new Rating();
        String viewName = ratingController.validate(rating, bindingResult, model);
        verify(ratingService).add(rating);
        assertThat(viewName).isEqualTo("rating/add");
    }

    @Test
    void showUpdateForm_shouldAddRatingToModelAndReturnViewName() {
        int id = 1;
        Rating rating = new Rating();
        when(ratingService.findById(id)).thenReturn(rating);
        String viewName = ratingController.showUpdateForm(id, model);
        verify(model).addAttribute("rating", rating);
        assertThat(viewName).isEqualTo("rating/update");
    }

    @Test
    void updateRating_shouldUpdateRatingAndRedirectToListView() {
        int id = 1;
        Rating rating = new Rating();
        String viewName = ratingController.updateRating(id, rating, bindingResult, model);
        verify(ratingService).update(id, rating);
        assertThat(viewName).isEqualTo("redirect:/rating/list");
    }

    @Test
    void deleteRating_shouldDeleteRatingAndRedirectToListView() {
        int id = 1;
        String viewName = ratingController.deleteRating(id, model);
        verify(ratingService).delete(id);
        assertThat(viewName).isEqualTo("redirect:/rating/list");
    }
}
