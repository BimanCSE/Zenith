package com.tw.bootcamp.bookshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Optional;

import static com.tw.bootcamp.bookshop.user.User.PASSWORD_ENCODER;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    public UserService() {
    }

    public User create(CreateUserCommand userCommand) throws InvalidEmailException {
        Optional<User> user = userRepository.findByEmail(userCommand.getEmail());
        if (user.isPresent()) {
            throw new InvalidEmailException();
        }
        //validator.validate(userCommand);

        User newUser = User.create(userCommand);
        validator.validate(newUser);

//        String password = "";
//        if (!userCommand.getPassword().isEmpty()) {
//            password = PASSWORD_ENCODER.encode(userCommand.getPassword());
//
//        }
//        User usr = new User(userCommand.getEmail(), password );
        return userRepository.save(newUser);
        //return userRepository.save(usr);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList()
        );
    }
}
