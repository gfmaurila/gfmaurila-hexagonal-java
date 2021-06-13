package br.com.gfmaurila.hexagonal.domain.services;

import br.com.gfmaurila.hexagonal.domain.entities.User;
import br.com.gfmaurila.hexagonal.domain.exceptions.UserExistsException;
import br.com.gfmaurila.hexagonal.domain.ports.UserRepository;

public class CreateNewUser {

    private UserRepository userRepo;

    public CreateNewUser(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User execute(User user) {
        User userFromDB = userRepo.findByEmail(user.getEmail());

        if (userFromDB != null) {
            throw new UserExistsException();
        }

        // Validar atributos??? :)

        return userRepo.save(user);
    }
}
