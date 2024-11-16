package org.poseidon.trading.service;

import org.poseidon.trading.domain.User;
import org.poseidon.trading.model.UserModel;

import java.util.List;

public interface UserService {

    /**
     * Finds a UserModel by its ID.
     *
     * @param id the ID of the UserModel to find
     * @return the UserModel with the given ID, or null if not found
     */
    UserModel findById(int id);

    /**
     * Retrieves all UserModel entities.
     *
     * @return a list of all UserModel entities
     */
    List<UserModel> findAll();

    /**
     * Adds a new UserModel.
     *
     * @param user the UserModel to add
     * @return the added UserModel
     */
    UserModel add(User user);

    /**
     * Updates an existing UserModel.
     *
     * @param user the UserModel with updated information
     * @return the updated UserModel
     */
    UserModel update(User user);

    /**
     * Deletes a UserModel by its ID.
     *
     * @param id the ID of the UserModel to delete
     */
    void delete(int id);
}
