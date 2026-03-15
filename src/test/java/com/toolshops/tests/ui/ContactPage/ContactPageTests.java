package com.toolshops.tests.ui.ContactPage;

import com.toolshop.framework.pages.ContactPage;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * @author Elnur Abaslı
 */
public class ContactPageTests extends BaseTest {

    private ContactPage contactPage;

    @BeforeEach
    void setup(){
        contactPage = new ContactPage(threadPage.get());
    }

    @Test
    @DisplayName("Verify Page Title")
    @Description("Checks if the correct title is displayed upon loading the page")
    void checkPageTitleTest(){
        contactPage.open();
        assertThat(contactPage.pageTitle).containsText(contactPage.pageTitleText);
    }
}
