package br.com.gfmaurila.hexagonal.domain.ports;

import java.util.List;

import br.com.gfmaurila.hexagonal.domain.entities.User;
import br.com.gfmaurila.hexagonal.domain.valueobjects.Email;

public interface UserRepository {

    public User save(User user);

    public User findByEmail(Email email);
    
    public List<User> findAll(int page, int size);
}
