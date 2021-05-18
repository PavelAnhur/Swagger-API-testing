package com.epam.data.provider;

import com.epam.enums.StatusCode;
import org.testng.annotations.DataProvider;

import static com.epam.tests.api.swagger.conditions.GetQueryConditions.getResponseBodyFileInvalid;
import static com.epam.tests.api.swagger.conditions.GetQueryConditions.getResponseBodyFileValid;

public class GetQueryData {

    @DataProvider (name = "dataForTest")
    public static Object[][] getData() {
        return new Object[][]{
                {null, StatusCode.OK_200.getValue(), getResponseBodyFileValid()},
                {null, StatusCode.NOT_FOUND_404.getValue(), getResponseBodyFileInvalid()}
        };
    }
}
