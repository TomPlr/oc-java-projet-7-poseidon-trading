package org.poseidon.trading.repositories;


import org.poseidon.trading.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
