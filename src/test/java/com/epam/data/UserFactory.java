package com.epam.data;

import static com.epam.utils.StringUtils.getRandomString;

public class UserFactory {

    public User getUser(String userType) {
        if (userType.equalsIgnoreCase("validUser")) {
            return new User.UserBuilder()
                    .id(1)
                    .userName("pavelanhur")
                    .firstName("Pavel")
                    .lastName("Anhur")
                    .email("pavelanhur@gmail.com")
                    .password("asdf1234")
                    .phone("+375336235840")
                    .userStatus(1)
                    .build();
        } else if (userType.equalsIgnoreCase("invalidUser")) {
            return new User.UserBuilder()
                    .id(1)
                    .userName(getRandomString(8))
                    .firstName(getRandomString(5))
                    .lastName(getRandomString(5))
                    .email(getRandomString(5) + "@gmail.com")
                    .password(getRandomString(8))
                    .phone("+43589234")
                    .userStatus(2)
                    .build();
        } else if (userType.equalsIgnoreCase("emptyUser")) {
            return new User.UserBuilder()
                    .build();
        } else {
            return null;
        }
    }
}
