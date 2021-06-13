package br.com.gfmaurila.hexagonal.adapters.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfmaurila.hexagonal.adapters.dto.LoginSession;
import br.com.gfmaurila.hexagonal.domain.entities.User;
import br.com.gfmaurila.hexagonal.domain.exceptions.EmailNotFoundException;
import br.com.gfmaurila.hexagonal.domain.exceptions.WrongPasswordException;
import br.com.gfmaurila.hexagonal.domain.ports.UserRepository;
import br.com.gfmaurila.hexagonal.domain.services.Login;
import br.com.gfmaurila.hexagonal.domain.valueobjects.Email;
import br.com.gfmaurila.hexagonal.domain.valueobjects.Password;

@RestController
@RequestMapping("/auth")
public class AuthRest {

    @Autowired
    private UserRepository userRepo;

    @PostMapping(path = "login")
    public LoginSession login(String email, String password) {
        Login login = new Login(userRepo);
        User user = login.execute(new Email(email), new Password(password));
        return new LoginSession(user, "fake token...:)");
    }

    @ExceptionHandler({ EmailNotFoundException.class, WrongPasswordException.class })
    public ResponseEntity<String> wrongEmailAndPassword(RuntimeException e) {
        return new ResponseEntity<String>("Usuário/Senha inválidos", HttpStatus.BAD_REQUEST);
    }
}
