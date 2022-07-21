package com.tw.bootcamp.bookshop.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class CreateUserCommand {


    private final String email;


    private final String password;
}
