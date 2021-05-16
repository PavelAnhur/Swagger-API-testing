package com.epam.data;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public static class UserBuilder {
        private final User newUser;

        public UserBuilder() {
            this.newUser = new User();
        }

        public UserBuilder id(final int id) {
            newUser.id = id;
            return this;
        }

        public UserBuilder userName(final String username) {
            newUser.userName = username;
            return this;
        }

        public UserBuilder firstName(final String firstname) {
            newUser.firstName = firstname;
            return this;
        }

        public UserBuilder lastName(final String lastname) {
            newUser.lastName = lastname;
            return this;
        }

        public UserBuilder email(final String email) {
            newUser.email = email;
            return this;
        }

        public UserBuilder password(final String password) {
            newUser.password = password;
            return this;
        }

        public UserBuilder phone(final String phone) {
            newUser.phone = phone;
            return this;
        }

        public UserBuilder userStatus(final int userStatus) {
            newUser.userStatus = userStatus;
            return this;
        }

        public User build() {
            return newUser;
        }
    }
}
