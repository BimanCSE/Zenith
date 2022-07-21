package com.tw.bootcamp.bookshop.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static com.tw.bootcamp.bookshop.user.UserTestBuilder.buildCreateUserCommand;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private UserService userService = new UserService();


    @Test
    void shouldCreateUserWithValidInputs() throws InvalidEmailException {
        CreateUserCommand userCredentials = new CreateUserCommandTestBuilder().build();
        User user = new UserTestBuilder().withEmail(userCredentials.getEmail()).build();
        when(userRepository.save(any(User.class))).thenReturn(user);
        UserCreateResponse createdUser = userService.create(userCredentials);
        ArgumentCaptor<User> argCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(argCaptor.capture());
        assertEquals(userCredentials.getEmail(), argCaptor.getValue().getEmail());
        assertEquals(user.getEmail(), createdUser.getEmail());
    }

    @Test
    void shouldNotCreateUserWhenUserWithSameEmailAlreadyExists() {
        CreateUserCommand userCommand = new CreateUserCommandTestBuilder().build();
        when(userRepository.findByEmail(userCommand.getEmail())).thenReturn(Optional.of(new User()));
        userRepository.save(User.create(userCommand));
        UserCreateResponse usr = userService.create(userCommand);
        assertEquals("User with same email already created", usr.getEmailValError());
    }
}