package com.epam.data.provider;

import com.epam.data.User;
import com.epam.data.Users;
import com.epam.enums.StatusCode;
import com.epam.utils.StringUtils;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;

import static com.epam.tests.api.swagger.conditions.GetQueryConditions.getResponseBodyFileInvalid;
import static com.epam.tests.api.swagger.conditions.GetQueryConditions.getResponseBodyFileValid;

public final class DataProviderForTests {
    private DataProviderForTests() {
    }

    private static final int LENGTH = 8;
    private static final Users VALID_USERS = Users.builder().users(Arrays.asList(
            User.builder().id(1).username("pavelanhur").firstName("Pavel").lastName("Anhur")
                    .email("pavelanhur@gmail.com").password("asdf1234").phone("+375336235840").userStatus(1).build(),
            User.builder().id(2).username("valeryanhur").firstName("Valery").lastName("Anhur")
                    .email("valeryaanhur@gmail.com").password("asdf1234").phone("+2342902523").userStatus(1).build()
    )).build();
    private static final Users INVALID_USERS = Users.builder().users(Collections.singletonList(
            User.builder()
                    .id(-1).username(StringUtils.getRandomString(LENGTH))
                    .firstName(StringUtils.getRandomString(LENGTH))
                    .lastName(StringUtils.getRandomString(LENGTH))
                    .email(StringUtils.getRandomString(LENGTH) + "@gmail.com")
                    .password(StringUtils.getRandomString(LENGTH))
                    .phone(StringUtils.getRandomString(LENGTH))
                    .userStatus(1).build()
    )).build();
    private static final Users EMPTY_USER = Users.builder().users(Collections.singletonList(
            User.builder().build()
    )).build();

    @DataProvider(name = "dataForPostTest")
    public static Object[][] getDataForPostQueryTest() {
        return new Object[][]{
                {VALID_USERS, StatusCode.OK_200.getValue()},
//                {INVALID_USERS, StatusCode.SERVER_ERROR_500.getValue()},
                {EMPTY_USER, StatusCode.SERVER_ERROR_500.getValue()}
        };
    }

    @DataProvider(name = "dataForGetTest")
    public static Object[][] getDataForGetQueryTest() {
        return new Object[][]{
                {VALID_USERS, StatusCode.OK_200.getValue(), getResponseBodyFileValid()},
                {INVALID_USERS, StatusCode.NOT_FOUND_404.getValue(), getResponseBodyFileInvalid()}
        };
    }

    @DataProvider (name = "dataForDeleteTest")
    public static Object[][] getDataForDeleteQueryTest() {
        return new Object[][]{
                {VALID_USERS, StatusCode.OK_200.getValue()},
                {INVALID_USERS, StatusCode.NOT_FOUND_404.getValue()}
        };
    }
}
