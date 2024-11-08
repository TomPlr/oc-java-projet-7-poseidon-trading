package org.poseidon.trading.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.poseidon.trading.domain.RuleName;
import org.poseidon.trading.repositories.RuleNameRepository;
import org.poseidon.trading.service.impl.RuleNameServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceImplTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Test
    void findById_shouldReturnRuleName_whenRuleNameExists() {
        int id = 1;
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findById(id)).thenReturn(Optional.of(ruleName));
        RuleName result = ruleNameService.findById(id);
        assertThat(result).isEqualTo(ruleName);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_whenRuleNameDoesNotExist() {
        int id = 1;
        when(ruleNameRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> ruleNameService.findById(id))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllRuleNames() {
        List<RuleName> ruleNames = Arrays.asList(new RuleName(), new RuleName());
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        List<RuleName> result = ruleNameService.findAll();
        assertThat(result).isEqualTo(ruleNames);
    }

    @Test
    void add_shouldSaveRuleName() {
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        RuleName result = ruleNameService.add(ruleName);
        assertThat(result).isEqualTo(ruleName);
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    void update_shouldUpdateRuleName_whenRuleNameExists() {
        int id = 1;
        RuleName existingRuleName = new RuleName();
        RuleName updatedRuleName = new RuleName();
        updatedRuleName.setName("New Name");
        updatedRuleName.setDescription("New Description");
        updatedRuleName.setJson("New Json");
        updatedRuleName.setTemplate("New Template");
        updatedRuleName.setSqlStr("New SqlStr");
        updatedRuleName.setSqlPart("New SqlPart");

        when(ruleNameRepository.findById(id)).thenReturn(Optional.of(existingRuleName));
        when(ruleNameRepository.save(existingRuleName)).thenReturn(updatedRuleName);

        RuleName result = ruleNameService.update(id, updatedRuleName);

        assertThat(result).isEqualTo(updatedRuleName);
        assertThat(existingRuleName.getName()).isEqualTo(updatedRuleName.getName());
        assertThat(existingRuleName.getDescription()).isEqualTo(updatedRuleName.getDescription());
        assertThat(existingRuleName.getJson()).isEqualTo(updatedRuleName.getJson());
        assertThat(existingRuleName.getTemplate()).isEqualTo(updatedRuleName.getTemplate());
        assertThat(existingRuleName.getSqlStr()).isEqualTo(updatedRuleName.getSqlStr());
        assertThat(existingRuleName.getSqlPart()).isEqualTo(updatedRuleName.getSqlPart());
        verify(ruleNameRepository, times(1)).save(existingRuleName);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenRuleNameDoesNotExist() {
        int id = 1;
        RuleName updatedRuleName = new RuleName();
        when(ruleNameRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> ruleNameService.update(id, updatedRuleName))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteRuleName() {
        int id = 1;
        ruleNameService.delete(id);
        verify(ruleNameRepository, times(1)).deleteById(id);
    }
}
