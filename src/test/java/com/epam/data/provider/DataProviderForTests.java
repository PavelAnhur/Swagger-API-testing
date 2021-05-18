package com.epam.data.provider;

import com.epam.data.request.User;
import com.epam.data.request.Users;
import com.epam.enums.StatusCode;
import com.epam.utils.StringUtils;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Collections;

public final class DataProviderForTests {
    private static final int LENGTH = 8;
    private static final Object EMPTY_USERS = null;
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
    private static final Users NOT_ALL_FIELDS_USERS = Users.builder().users(Arrays.asList(
            User.builder().id(2).firstName("Jhon").build(),
            User.builder().userStatus(2).username("hhhhh").build()
    )).build();

    private DataProviderForTests() {
    }

    @DataProvider(name = "dataForPostTest")
    public static Object[][] getDataForPostQueryTest() {
        return new Object[][]{
                {VALID_USERS, StatusCode.OK_200.getValue()},
//                {INVALID_USERS, StatusCode.SERVER_ERROR_500.getValue()},
                {NOT_ALL_FIELDS_USERS, StatusCode.OK_200.getValue()},
                {EMPTY_USERS, StatusCode.SERVER_ERROR_500.getValue()}
        };
    }

    @DataProvider(name = "dataForGetTest")
    public static Object[][] getDataForGetQueryTest() {
        return new Object[][]{
                {getUser(VALID_USERS, 0), StatusCode.OK_200.getValue()},
                {getUser(INVALID_USERS, 1), StatusCode.NOT_FOUND_404.getValue()}
        };
    }

    @DataProvider(name = "dataForDeleteTest")
    public static Object[][] getDataForDeleteQueryTest() {
        return new Object[][]{
                {getUser(VALID_USERS, 1), StatusCode.OK_200.getValue()},
                {getUser(INVALID_USERS, 1), StatusCode.NOT_FOUND_404.getValue()}
        };
    }

    private static User getUser(final Users users, final int index) {
        if (users.equals(VALID_USERS)) {
            try {
                return VALID_USERS.getUsers().get(index);
            } catch (IndexOutOfBoundsException exception) {
                return VALID_USERS.getUsers().get(0);
            }
        } else if (users.equals(INVALID_USERS)) {
            try {
                return INVALID_USERS.getUsers().get(index);
            } catch (IndexOutOfBoundsException exception) {
                return INVALID_USERS.getUsers().get(0);
            }
        } else {
            return null;
        }
    }
}
