package com.epam.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private final int id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;
    private final int userStatus;
}

