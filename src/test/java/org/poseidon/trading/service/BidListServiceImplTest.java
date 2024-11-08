package org.poseidon.trading.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.BidList;
import org.poseidon.trading.repositories.BidListRepository;
import org.poseidon.trading.service.impl.BidListServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidListServiceImplTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListServiceImpl bidListService;


    @Test
    void findById_shouldReturnBidList_whenBidListExists() {
        int id = 1;
        BidList bidList = new BidList();

        when(bidListRepository.findById(id)).thenReturn(Optional.of(bidList));

        BidList result = bidListService.findById(id);

        assertThat(result).isEqualTo(bidList);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenBidListDoesNotExist() {
        int id = 1;

        when(bidListRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bidListService.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllBidLists() {
        List<BidList> bidLists = Arrays.asList(new BidList(), new BidList());
        when(bidListRepository.findAll()).thenReturn(bidLists);

        List<BidList> result = bidListService.findAll();

        assertThat(result).isEqualTo(bidLists);
    }

    @Test
    void add_shouldSaveBidList() {
        BidList bidList = new BidList();
        when(bidListRepository.save(bidList)).thenReturn(bidList);

        BidList result = bidListService.add(bidList);

        assertThat(result).isEqualTo(bidList);
        verify(bidListRepository, times(1)).save(bidList);
    }

    @Test
    void update_shouldUpdateBidList_whenBidListExists() {
        int id = 1;

        BidList existingBidList = new BidList();
        BidList updatedBidList = new BidList();
        updatedBidList.setAccount("UpdatedAccount");
        updatedBidList.setType("UpdatedType");
        updatedBidList.setBidQuantity(100d);

        when(bidListRepository.findById(id)).thenReturn(Optional.of(existingBidList));
        when(bidListRepository.save(existingBidList)).thenReturn(updatedBidList);

        BidList result = bidListService.update(id, updatedBidList);

        assertThat(result).isEqualTo(updatedBidList);
        assertThat(existingBidList.getAccount()).isEqualTo(updatedBidList.getAccount());
        assertThat(existingBidList.getType()).isEqualTo(updatedBidList.getType());
        assertThat(existingBidList.getBidQuantity()).isEqualTo(updatedBidList.getBidQuantity());
        verify(bidListRepository, times(1)).save(existingBidList);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenBidListDoesNotExist() {
        int id = 1;
        BidList updatedBidList = new BidList();
        when(bidListRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bidListService.update(id, updatedBidList))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteBidList() {
        int id = 1;

        bidListService.delete(id);

        verify(bidListRepository, times(1)).deleteById(id);
    }
}
