package com.epam.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private final int id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;
    private final int userStatus;
}

