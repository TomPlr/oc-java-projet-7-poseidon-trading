package org.poseidon.trading.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.poseidon.trading.domain.BidList;
import org.poseidon.trading.repositories.BidListRepository;
import org.poseidon.trading.service.BidListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;

    public BidListServiceImpl(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @Override
    public BidList findById(int id) {
        return bidListRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public BidList add(BidList bidList) {
        return bidListRepository.save(bidList);
    }

    @Override
    public BidList update(int id, BidList bidList) {
        BidList updatedBid = bidListRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(bidList.getAccount()).ifPresent(updatedBid::setAccount);
        Optional.ofNullable(bidList.getType()).ifPresent(updatedBid::setType);
        Optional.ofNullable(bidList.getBidQuantity()).ifPresent(updatedBid::setBidQuantity);

        return bidListRepository.save(updatedBid);
    }

    @Override
    public void delete(int id) {
        bidListRepository.deleteById(id);
    }
}
