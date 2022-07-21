package com.tw.bootcamp.bookshop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.tw.bootcamp.bookshop.user.User.PASSWORD_ENCODER;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    public UserService() {
    }

//    public User create(CreateUserCommand userCommand) throws InvalidEmailException {
//        Optional<User> user = userRepository.findByEmail(userCommand.getEmail());
//        if (user.isPresent()) {
//            throw new InvalidEmailException();
//        }
//        User newUser = User.create(userCommand);
//        validator.validate(newUser);
//        return userRepository.save(newUser);
//    }

    public UserCreateResponse create(CreateUserCommand userCommand) {
        String regex = "[A-Za-z0-9.-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,3}";
        Pattern pattern = Pattern.compile(regex);

        Optional<User> user = userRepository.findByEmail(userCommand.getEmail());

        UserCreateResponse userCreateResponse = new UserCreateResponse(userCommand.getEmail(), userCommand.getPassword());

        if (user.isPresent()) {
            userCreateResponse.setEmailValError("User with same email already created");
        } else if (userCreateResponse.getEmail().trim().length() == 0) {
            userCreateResponse.setEmailValError("Email is mandatory");
        } else if (!pattern.matcher(userCreateResponse.getEmail()).matches()) {
            userCreateResponse.setEmailValError("Invalid Email");
        }

        if(userCreateResponse.getPassword().trim().length() == 0){
            userCreateResponse.setPasswordValError("Password is mandatory");
        }else if(userCreateResponse.getPassword().trim().length()<8){
            userCreateResponse.setPasswordValError("Invalid password");
        }
        if(userCreateResponse.getEmailValError() == null && userCreateResponse.getPasswordValError() == null) {
            User newUser = User.create(userCommand);
            userRepository.save(newUser);
        }
        else{
            userCreateResponse.setPassword(null);
            userCreateResponse.setEmail(null);
        }
        return userCreateResponse;
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
