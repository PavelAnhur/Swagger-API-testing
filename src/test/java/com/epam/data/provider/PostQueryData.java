package com.epam.data.provider;

import com.epam.data.User;
import com.epam.data.Users;
import com.epam.enums.StatusCode;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class PostQueryData {
    private static final String REQUEST_BODY_VALID = "post-request-body-valid.json";
    private static final String REQUEST_BODY_INVALID = "post-query-body-invalid.json";
    private static final String REQUEST_BODY_EMPTY = "post-query-body-empty.json";

    @DataProvider
    public static Object[][] dataForQuery() {
        return new Object[][]{
                {Users.builder().users(Arrays.asList(
                        User.builder()
                                .id(1)
                                .userName("pavelanhur")
                                .firstName("Pavel")
                                .lastName("Anhur")
                                .email("pavelanhur@gmail.com")
                                .password("asdf1234")
                                .phone("+375336235840")
                                .userStatus(1).build(),
                        User.builder().id(2)
                                .userName("valeryanhur")
                                .firstName("Valery")
                                .lastName("Anhur")
                                .email("valeryaanhur@gmail.com")
                                .password("asdf1234")
                                .phone("+2342902523")
                                .userStatus(1).build())), StatusCode.OK_200.getValue()},
//                {REQUEST_BODY_EMPTY, StatusCode.SERVER_ERROR_500.getValue()},
//                {REQUEST_BODY_VALID, StatusCode.OK_200.getValue()}
        };
    }
}
