package com.epam.retry;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RetryFailedTestCases implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < maxRetryCount) {
            log.info("Retrying {} and the count is {}", iTestResult.getName(), retryCount + 1);
            retryCount++;
            return true;
        }
        return false;
    }
}
