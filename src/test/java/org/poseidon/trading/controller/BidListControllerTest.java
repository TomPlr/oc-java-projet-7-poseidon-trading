package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.BidList;
import org.poseidon.trading.service.impl.BidListServiceImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BidListControllerTest {

    @Mock
    private BidListServiceImpl bidListService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private BidListController bidListController;

    @Test
    void home_shouldAddBidListsToModelAndReturnViewName() {
        List<BidList> bidLists = Arrays.asList(new BidList(), new BidList());

        when(bidListService.findAll()).thenReturn(bidLists);

        String viewName = bidListController.home(model);

        verify(model).addAttribute("bidLists", bidLists);
        assertThat(viewName).isEqualTo("bidList/list");
    }


    @Test
    void validate_shouldAddBidListAndRedirectToListView_whenBindingResultHasNoErrors() {
        BidList bidList = new BidList();
        String viewName = bidListController.validate(bidList, bindingResult);
        verify(bidListService).add(bidList);
        assertThat(viewName).isEqualTo("bidList/list");
    }

    @Test
    void showUpdateForm_shouldAddBidListToModelAndReturnViewName() {
        int id = 1;
        BidList bidList = new BidList();
        when(bidListService.findById(id)).thenReturn(bidList);
        String viewName = bidListController.showUpdateForm(id, model);
        verify(model).addAttribute(bidList);
        assertThat(viewName).isEqualTo("bidList/update");
    }

    @Test
    void updateBid_shouldUpdateBidListAndRedirectToListView_whenBindingResultHasNoErrors() {
        int id = 1;
        BidList bidList = new BidList();
        String viewName = bidListController.updateBid(id, bidList, bindingResult);
        verify(bidListService).update(id, bidList);
        assertThat(viewName).isEqualTo("bidList/list");
    }

    @Test
    void deleteBid_shouldDeleteBidListAndRedirectToListView() {
        int id = 1;
        String viewName = bidListController.deleteBid(id, model);
        verify(bidListService).delete(id);
        assertThat(viewName).isEqualTo("bidList/list");
    }
}
