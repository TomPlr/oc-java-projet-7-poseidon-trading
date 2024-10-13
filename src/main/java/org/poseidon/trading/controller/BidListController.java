package org.poseidon.trading.controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.poseidon.trading.domain.BidList;
import org.poseidon.trading.service.impl.BidListServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class BidListController {

    private final BidListServiceImpl bidListService;

    public BidListController(BidListServiceImpl bidListService) {
        this.bidListService = bidListService;
    }

    @GetMapping("/bidList/list" )
    public String home(Model model) {
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bid, BindingResult result) {

        try {
            bidListService.add(bid);
            return "redirect:/bidList/list";
        } catch (Exception e) {
           log.error(e.getMessage());
        }

        return "redirect:/bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        model.addAttribute(bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result) {

        try{
            bidListService.update(id,bidList);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.delete(id);
        model.addAttribute("bidLists", bidListService.findAll());

        return "redirect:/bidList/list";
    }
}