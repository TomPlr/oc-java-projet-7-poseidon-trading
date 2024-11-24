package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.CurvePoint;
import org.poseidon.trading.service.impl.CurvePointServiceImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurveControllerTest {

    @Mock
    private CurvePointServiceImpl curvePointService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private CurveController curveController;

    @Test
    void home_shouldAddCurvePointsToModelAndReturnViewName() {
        List<CurvePoint> curvePoints = Arrays.asList(new CurvePoint(), new CurvePoint());
        when(curvePointService.findAll()).thenReturn(curvePoints);
        String viewName = curveController.home(model);
        verify(model).addAttribute("curvePoints", curvePoints);
        assertThat(viewName).isEqualTo("curvePoint/list");
    }

    @Test
    void addBidForm_shouldReturnViewName() {
        String viewName = curveController.addBidForm(new CurvePoint());
        assertThat(viewName).isEqualTo("curvePoint/add");
    }

    @Test
    void validate_shouldAddCurvePointAndReturnViewName() {
        CurvePoint curvePoint = new CurvePoint();
        String viewName = curveController.validate(curvePoint, bindingResult, model);
        verify(curvePointService).add(curvePoint);
        assertThat(viewName).isEqualTo("redirect:/curvePoint/list");
    }

    @Test
    void showUpdateForm_shouldAddCurvePointToModelAndReturnViewName() {
        int id = 1;
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointService.findById(id)).thenReturn(curvePoint);
        String viewName = curveController.showUpdateForm(id, model);
        verify(model).addAttribute("curvePoint", curvePoint);
        assertThat(viewName).isEqualTo("curvePoint/update");
    }

    @Test
    void updateBid_shouldUpdateCurvePointAndRedirectToListView() {
        int id = 1;
        CurvePoint curvePoint = new CurvePoint();
        String viewName = curveController.updateBid(id, curvePoint, bindingResult, model);
        verify(curvePointService).update(id, curvePoint);
        assertThat(viewName).isEqualTo("redirect:/curvePoint/list");
    }

    @Test
    void deleteBid_shouldDeleteCurvePointAndRedirectToListView() {
        int id = 1;
        String viewName = curveController.deleteBid(id, model);
        verify(curvePointService).delete(id);
        assertThat(viewName).isEqualTo("curvePoint/list");
    }
}
