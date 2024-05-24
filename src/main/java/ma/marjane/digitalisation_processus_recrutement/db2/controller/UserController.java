package ma.marjane.digitalisation_processus_recrutement.db2.controller;

import lombok.RequiredArgsConstructor;
import ma.marjane.digitalisation_processus_recrutement.db2.entity.User;
import ma.marjane.digitalisation_processus_recrutement.db2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }
}
