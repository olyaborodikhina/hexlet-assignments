package exercise.controller;
import exercise.model.User;
import exercise.model.QUser;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.data.querydsl.binding.QuerydslPredicate;
 import com.querydsl.core.types.Predicate;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUsersByName(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName
            ) {
        Iterable<User> users = userRepository.findAll();
        if(firstName != null)
            users = userRepository.findAll(QUser.user.firstName.toLowerCase().contains(firstName.toLowerCase(Locale.ROOT)));
        if(lastName != null)
            users = userRepository.findAll(QUser.user.lastName.toLowerCase().contains(lastName.toLowerCase(Locale.ROOT)));
        if(lastName != null && firstName != null) {
            users = userRepository.findAll(QUser.user.firstName.toLowerCase().contains(firstName.toLowerCase(Locale.ROOT)).and(QUser.user.lastName.toLowerCase().contains(lastName.toLowerCase(Locale.ROOT))));
        }
            return users;
    }


    // END
}

