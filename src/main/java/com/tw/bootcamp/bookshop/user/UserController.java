package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


//    @PostMapping
//    ResponseEntity<UserView> create(@RequestBody CreateUserCommand userCommand) throws InvalidEmailException {
//        User user = userService.create(userCommand);
//        return new ResponseEntity<>(new UserView(user), HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public HashMap<String,String> create(@RequestBody CreateUserCommand userCommand){
        UserCreateResponse result = userService.create(userCommand);
        HashMap<String,String> map = new HashMap<>();
        if(result.getEmailValError() == null && result.getPasswordValError() == null){
            map.put("Email" , result.getEmail());
            map.put("Password" , result.getPassword());
        }else{
            map.put("Email" , userCommand.getEmail());
            map.put("Password" , userCommand.getPassword());
            map.put("Email Validation Error" , result.getEmailValError());
            map.put("Password Validation Error" , result.getPasswordValError());
        }
        return map;
    }

}
