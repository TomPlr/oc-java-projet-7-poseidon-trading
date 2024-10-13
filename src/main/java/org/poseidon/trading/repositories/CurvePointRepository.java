package org.poseidon.trading.repositories;


import org.poseidon.trading.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {
}
