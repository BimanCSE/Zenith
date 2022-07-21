package com.tw.bootcamp.bookshop.user;

public class CreateUserCommandTestBuilder {
    private CreateUserCommand.CreateUserCommandBuilder commandBuilder;

    public CreateUserCommandTestBuilder() {
        commandBuilder = CreateUserCommand.builder()
                .email("testemail@test.com")
                .password("foobarok");
    }

    CreateUserCommand build() {
        return commandBuilder.build();
    }

    public CreateUserCommandTestBuilder withEmptyEmail() {
        commandBuilder.email("");
        commandBuilder.password("foobar");
        return this;
    }

    public CreateUserCommandTestBuilder withEmptyPassword() {
        commandBuilder.password("");
        return this;
    }
}
