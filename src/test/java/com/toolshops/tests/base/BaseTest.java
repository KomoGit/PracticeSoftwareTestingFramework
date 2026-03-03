package com.toolshops.tests.base;

import com.microsoft.playwright.*;
import com.toolshop.framework.models.FrameworkConfig;
import com.toolshop.framework.utils.ConfigReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestWatcher.class)
public abstract class BaseTest {
    protected Playwright playwright;
    protected FrameworkConfig config;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    void setup(){
        config = ConfigReader.readConfig();
        playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(config.headless);

        switch (config.browser.toLowerCase()) {
            case "firefox" -> browser = playwright.firefox().launch(options);
            case "webkit" -> browser = playwright.webkit().launch(options);
            default -> browser = playwright.chromium().launch(options);
        }
    }

    @BeforeEach
    void createContext() {
        context = browser.newContext();

        //Custom timeouts.
        context.setDefaultTimeout(config.timeout);
        context.setDefaultNavigationTimeout(config.timeout);

        page = context.newPage();
        page.navigate(config.baseUrl);
    }

    @AfterEach
    void closeContext() {
        if (context != null) context.close();
    }

    @AfterAll
    void teardown(){
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    public Page getPage(){
        return this.page;
    }
}