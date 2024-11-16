package org.poseidon.trading.service;

import org.poseidon.trading.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {

    /**
     * Finds a CurvePoint by its ID.
     *
     * @param id the ID of the CurvePoint to find
     * @return the CurvePoint with the given ID, or null if not found
     */
    CurvePoint findById(int id);

    /**
     * Retrieves all CurvePoint entities.
     *
     * @return a list of all CurvePoint entities
     */
    List<CurvePoint> findAll();

    /**
     * Adds a new CurvePoint.
     *
     * @param curvePoint the CurvePoint to add
     * @return the added CurvePoint
     */
    CurvePoint add(CurvePoint curvePoint);

    /**
     * Updates an existing CurvePoint with the given ID.
     *
     * @param id         the ID of the CurvePoint to update
     * @param curvePoint the CurvePoint with updated information
     * @return the updated CurvePoint
     */
    CurvePoint update(int id, CurvePoint curvePoint);

    /**
     * Deletes a CurvePoint by its ID.
     *
     * @param id the ID of the CurvePoint to delete
     */
    void delete(int id);
}
