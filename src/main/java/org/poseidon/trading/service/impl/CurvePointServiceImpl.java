package org.poseidon.trading.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.poseidon.trading.domain.CurvePoint;
import org.poseidon.trading.repositories.CurvePointRepository;
import org.poseidon.trading.service.CurvePointService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    CurvePointRepository curvePointRepository;

    public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    @Override
    public CurvePoint findById(int id) {
        return curvePointRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint add(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public CurvePoint update(int id, CurvePoint curvePoint) {
        CurvePoint updatedCurvePoint = curvePointRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(curvePoint.getTerm()).ifPresent(updatedCurvePoint::setTerm);
        Optional.ofNullable(curvePoint.getValue()).ifPresent(updatedCurvePoint::setValue);
        return curvePointRepository.save(curvePoint);
    }

    @Override
    public void delete(int id) {
        curvePointRepository.deleteById(id);
    }
}
