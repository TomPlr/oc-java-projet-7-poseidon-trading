package org.poseidon.trading.repositories;


import org.poseidon.trading.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
