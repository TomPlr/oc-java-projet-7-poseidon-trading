package org.poseidon.trading.service;

import org.poseidon.trading.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {

    CurvePoint findById(int id);

    List<CurvePoint> findAll();

    CurvePoint add(CurvePoint curvePoint);

    CurvePoint update(int id, CurvePoint curvePoint);

    void delete(int id);
}
