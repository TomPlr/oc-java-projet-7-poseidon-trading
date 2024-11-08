package org.poseidon.trading.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.poseidon.trading.assembler.UserAssembler;
import org.poseidon.trading.domain.User;
import org.poseidon.trading.model.UserModel;
import org.poseidon.trading.repositories.UserRepository;
import org.poseidon.trading.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserAssembler userAssembler;

    public UserServiceImpl(UserRepository userRepository, UserAssembler userAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
    }

    @Override
    public UserModel findById(int id) {
        return userAssembler.toModel(userRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll().stream().map(userAssembler::toModel).toList();
    }

    @Override
    public UserModel add(User user) {
        return userAssembler.toModel(userRepository.save(user));
    }

    @Override
    public UserModel update(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //TODO: Replace DB call by getting the user authenticated
        User updatedUser = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(user.getUsername()).ifPresent(updatedUser::setUsername);
        Optional.ofNullable(user.getFullname()).ifPresent(updatedUser::setFullname);
        Optional.ofNullable(user.getPassword()).ifPresent(updatedUser::setPassword);

        return userAssembler.toModel(userRepository.save(updatedUser));
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
