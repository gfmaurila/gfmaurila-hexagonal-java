package br.com.gfmaurila.hexagonal.domain.services;

import br.com.gfmaurila.hexagonal.domain.entities.User;
import br.com.gfmaurila.hexagonal.domain.exceptions.EmailNotFoundException;
import br.com.gfmaurila.hexagonal.domain.exceptions.WrongPasswordException;
import br.com.gfmaurila.hexagonal.domain.ports.UserRepository;
import br.com.gfmaurila.hexagonal.domain.valueobjects.Email;
import br.com.gfmaurila.hexagonal.domain.valueobjects.Password;

public class Login {

    private UserRepository userRepo;

    public Login(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User execute(Email email, Password password) {
        User userFromDB = userRepo.findByEmail(email);
        if (userFromDB == null) {
            throw new EmailNotFoundException();
        }

        Password expected = userFromDB.getPassword();

        if (!expected.same(password)) {
            throw new WrongPasswordException();
        }

        return userFromDB;
    }
}
