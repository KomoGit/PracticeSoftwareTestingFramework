package com.toolshops.tests.base;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayInputStream;

public class TestWatcher implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        BaseTest testInstance = (BaseTest) context.getRequiredTestInstance();

        if(BaseTest.config.screenshotOnFailure && context.getExecutionException().isPresent()) {
                byte[] screenshot = testInstance.getPage().screenshot();
                Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
    }
}