package org.poseidon.trading.service;

import org.poseidon.trading.domain.User;
import org.poseidon.trading.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel findById(int id);

    List<UserModel> findAll();

    UserModel add(User user);

    UserModel update(User user);

    void delete(int id);
}
