package com.toolshops.tests.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.toolshop.framework.models.FrameworkConfig;
import com.toolshop.framework.utils.ConfigReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseApiTest {
    Playwright playwright;
    protected APIRequestContext request;
    protected FrameworkConfig config;

    @BeforeAll
    void setupApiFramework() {
        config = ConfigReader.readConfig();
        playwright = Playwright.create();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(config.baseUrl)
                .setExtraHTTPHeaders(headers));
    }

    @AfterAll
    void teardownApiFramework() {
        if (request != null) request.dispose();
        if (playwright != null) playwright.close();
    }
}