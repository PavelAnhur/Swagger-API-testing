package com.epam.data;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class Users {
    private final List<User> users;
}
