package com.toolshops.tests.base;

import com.microsoft.playwright.*;
import com.toolshop.framework.models.FrameworkConfig;
import com.toolshop.framework.utils.ConfigReader;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(TestWatcher.class)
public abstract class BaseTest {

    protected static FrameworkConfig config;

    // Everything related to Playwright MUST be ThreadLocal
    protected static final ThreadLocal<Playwright> threadPlaywright = new ThreadLocal<>();
    protected static final ThreadLocal<Browser> threadBrowser = new ThreadLocal<>();
    protected static final ThreadLocal<BrowserContext> threadContext = new ThreadLocal<>();
    protected static final ThreadLocal<Page> threadPage = new ThreadLocal<>();

    @BeforeAll
    static void setupConfig() {
        config = ConfigReader.readConfig();
    }

    @BeforeEach
    void setupPlaywright() {
        // 1. Create a Playwright instance for this thread
        Playwright playwright = Playwright.create();
        threadPlaywright.set(playwright);

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(config.IsHeadless);

        // 2. Launch a Browser for this thread
        Browser browser = switch (config.browser.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };
        threadBrowser.set(browser);

        // 3. Create Context and Page
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setBaseURL(config.baseUrl));
        context.setDefaultTimeout(config.timeout);
        context.setDefaultNavigationTimeout(config.timeout);
        threadContext.set(context);

        Page page = context.newPage();
        page.navigate(config.baseUrl);
        threadPage.set(page);
    }

    @AfterEach
    void teardownPlaywright() {
        // Clean up everything for this thread to prevent memory leaks
        if (threadPage.get() != null) threadPage.remove();

        if (threadContext.get() != null) {
            threadContext.get().close();
            threadContext.remove();
        }

        if (threadBrowser.get() != null) {
            threadBrowser.get().close();
            threadBrowser.remove();
        }

        if (threadPlaywright.get() != null) {
            threadPlaywright.get().close();
            threadPlaywright.remove();
        }
    }

    public Page getPage() {
        return threadPage.get();
    }
}