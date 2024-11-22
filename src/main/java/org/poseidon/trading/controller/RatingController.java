package org.poseidon.trading.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.poseidon.trading.domain.Rating;
import org.poseidon.trading.service.impl.RatingServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class RatingController {

    RatingServiceImpl ratingService;

    public RatingController(final RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "rating/add";
        }

        ratingService.add(rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("rating", ratingService.findById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating,
                               BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.update(id, rating);
            return "redirect:/rating/list";
        }
        return "rating/update";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.delete(id);
        return "rating/list";
    }
}
