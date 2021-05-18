package com.epam.data.provider;

import com.epam.enums.StatusCode;
import org.testng.annotations.DataProvider;

public final class DeleteQueryData {
    private DeleteQueryData() {
    }

    @DataProvider (name = "deleteQueryData")
    public static Object[][] getData() {
        return new Object[][]{
                {null, StatusCode.OK_200.getValue()},
                {null, StatusCode.NOT_FOUND_404.getValue()}
        };
    }
}
