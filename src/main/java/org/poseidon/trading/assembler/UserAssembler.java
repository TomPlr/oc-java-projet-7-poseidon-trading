package org.poseidon.trading.assembler;

import org.poseidon.trading.domain.User;
import org.poseidon.trading.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    public UserModel toModel(User user) {
        return new UserModel(user.getUsername(), user.getFullname(), user.getRole());
    }
}
