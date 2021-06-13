package br.com.gfmaurila.hexagonal.domain.services;

import java.util.List;
import java.util.Optional;

import br.com.gfmaurila.hexagonal.domain.entities.User;
import br.com.gfmaurila.hexagonal.domain.ports.UserRepository;

public class AllUsers {
    private UserRepository userRepo;

    public AllUsers(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> execute(Optional<Integer> page, Optional<Integer> size) {
        int validPage = page.isPresent() && page.get() >= 0 ? page.get() : 0;
        int validSize = size.isPresent() && size.get() <= 50 && size.get() > 0 ? size.get() : 10;
        return userRepo.findAll(validPage, validSize);
    }
}
