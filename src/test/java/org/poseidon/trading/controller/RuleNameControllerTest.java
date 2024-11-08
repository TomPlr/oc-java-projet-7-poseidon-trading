package org.poseidon.trading.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.RuleName;
import org.poseidon.trading.service.impl.RuleNameServiceImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTest {

    @Mock
    private RuleNameServiceImpl ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RuleNameController ruleNameController;

    @Test
    void home_shouldAddRuleNamesToModelAndReturnViewName() {
        List<RuleName> ruleNames = Arrays.asList(new RuleName(), new RuleName());
        when(ruleNameService.findAll()).thenReturn(ruleNames);
        String viewName = ruleNameController.home(model);
        verify(model).addAttribute("ruleNames", ruleNames);
        assertThat(viewName).isEqualTo("ruleName/list");
    }

    @Test
    void addRuleForm_shouldReturnViewName() {
        String viewName = ruleNameController.addRuleForm(new RuleName());
        assertThat(viewName).isEqualTo("ruleName/add");
    }

    @Test
    void validate_shouldAddRuleNameAndReturnViewName() {
        RuleName ruleName = new RuleName();
        String viewName = ruleNameController.validate(ruleName, bindingResult, model);
        verify(ruleNameService).add(ruleName);
        assertThat(viewName).isEqualTo("ruleName/add");
    }

    @Test
    void showUpdateForm_shouldAddRuleNameToModelAndReturnViewName() {
        int id = 1;
        RuleName ruleName = new RuleName();
        when(ruleNameService.findById(id)).thenReturn(ruleName);
        String viewName = ruleNameController.showUpdateForm(id, model);
        verify(model).addAttribute("ruleName", ruleName);
        assertThat(viewName).isEqualTo("ruleName/update");
    }

    @Test
    void updateRuleName_shouldUpdateRuleNameAndRedirectToListView() {
        int id = 1;
        RuleName ruleName = new RuleName();
        String viewName = ruleNameController.updateRuleName(id, ruleName, bindingResult, model);
        verify(ruleNameService).update(id, ruleName);
        assertThat(viewName).isEqualTo("redirect:/ruleName/list");
    }

    @Test
    void deleteRuleName_shouldDeleteRuleNameAndRedirectToListView() {
        int id = 1;
        String viewName = ruleNameController.deleteRuleName(id, model);
        verify(ruleNameService).delete(id);
        assertThat(viewName).isEqualTo("redirect:/ruleName/list");
    }
}
