package org.poseidon.trading.service;

import org.poseidon.trading.domain.RuleName;

import java.util.List;

public interface RuleNameService {

    RuleName findById(int id);

    List<RuleName> findAll();

    RuleName add(RuleName ruleName);

    RuleName update(int id, RuleName ruleName);

    void delete(int id);

}
