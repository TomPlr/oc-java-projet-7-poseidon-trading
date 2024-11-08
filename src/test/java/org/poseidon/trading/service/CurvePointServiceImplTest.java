package org.poseidon.trading.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.CurvePoint;
import org.poseidon.trading.repositories.CurvePointRepository;
import org.poseidon.trading.service.impl.CurvePointServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceImplTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointServiceImpl curvePointService;

    @Test
    void findById_shouldReturnCurvePoint_whenCurvePointExists() {
        int id = 1;
        CurvePoint curvePoint = new CurvePoint();

        when(curvePointRepository.findById(id)).thenReturn(Optional.of(curvePoint));

        CurvePoint result = curvePointService.findById(id);

        assertThat(result).isEqualTo(curvePoint);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenCurvePointDoesNotExist() {
        int id = 1;

        when(curvePointRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> curvePointService.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllCurvePoints() {
        List<CurvePoint> curvePoints = Arrays.asList(new CurvePoint(), new CurvePoint());

        when(curvePointRepository.findAll()).thenReturn(curvePoints);

        List<CurvePoint> result = curvePointService.findAll();

        assertThat(result).isEqualTo(curvePoints);
    }

    @Test
    void add_shouldSaveCurvePoint() {
        CurvePoint curvePoint = new CurvePoint();

        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        CurvePoint result = curvePointService.add(curvePoint);

        assertThat(result).isEqualTo(curvePoint);
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void update_shouldUpdateCurvePoint_whenCurvePointExists() {
        int id = 1;
        CurvePoint existingCurvePoint = new CurvePoint();
        CurvePoint updatedCurvePoint = new CurvePoint();
        updatedCurvePoint.setTerm(10d);
        updatedCurvePoint.setValue(0.05);

        when(curvePointRepository.findById(id)).thenReturn(Optional.of(existingCurvePoint));
        when(curvePointRepository.save(updatedCurvePoint)).thenReturn(updatedCurvePoint);

        CurvePoint result = curvePointService.update(id, updatedCurvePoint);

        assertThat(result).isEqualTo(updatedCurvePoint);
        assertThat(existingCurvePoint.getTerm()).isEqualTo(updatedCurvePoint.getTerm());
        assertThat(existingCurvePoint.getValue()).isEqualTo(updatedCurvePoint.getValue());
        verify(curvePointRepository, times(1)).save(updatedCurvePoint);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenCurvePointDoesNotExist() {
        int id = 1;
        CurvePoint updatedCurvePoint = new CurvePoint();

        when(curvePointRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> curvePointService.update(id, updatedCurvePoint))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteCurvePoint() {
        int id = 1;

        curvePointService.delete(id);

        verify(curvePointRepository, times(1)).deleteById(id);
    }
}
