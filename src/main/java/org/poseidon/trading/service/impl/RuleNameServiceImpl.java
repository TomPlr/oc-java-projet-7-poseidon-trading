package org.poseidon.trading.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.poseidon.trading.domain.RuleName;
import org.poseidon.trading.repositories.RuleNameRepository;
import org.poseidon.trading.service.RuleNameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameServiceImpl implements RuleNameService {

    RuleNameRepository ruleNameRepository;

    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
        this.ruleNameRepository = ruleNameRepository;
    }

    @Override
    public RuleName findById(int id) {
        return ruleNameRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName add(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    @Override
    public RuleName update(int id, RuleName ruleName) {
        RuleName updatedRuleName = ruleNameRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(ruleName.getName()).ifPresent(updatedRuleName::setName);
        Optional.ofNullable(ruleName.getDescription()).ifPresent(updatedRuleName::setDescription);
        Optional.ofNullable(ruleName.getJson()).ifPresent(updatedRuleName::setJson);
        Optional.ofNullable(ruleName.getTemplate()).ifPresent(updatedRuleName::setTemplate);
        Optional.ofNullable(ruleName.getSqlStr()).ifPresent(updatedRuleName::setSqlStr);
        Optional.ofNullable(ruleName.getSqlPart()).ifPresent(updatedRuleName::setSqlPart);

        return ruleNameRepository.save(updatedRuleName);
    }

    @Override
    public void delete(int id) {
        ruleNameRepository.deleteById(id);
    }
}
