package org.poseidon.trading.service;

import org.poseidon.trading.domain.BidList;

import java.util.List;

public interface BidListService {

    BidList findById(int id);

    List<BidList> findAll();

    BidList add(BidList bidList);

    BidList update(int id , BidList bidList);

    void delete(int id);
}
