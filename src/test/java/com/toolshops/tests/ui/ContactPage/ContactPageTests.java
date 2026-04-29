package com.toolshops.tests.ui.ContactPage;

import com.toolshop.framework.pages.ContactPage;
import com.toolshop.framework.utils.FileUtils;
import com.toolshops.tests.base.BaseTest;
import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

class ContactPageTests extends BaseTest {

    private ContactPage contactPage;

    @BeforeEach
    void setup(){
        contactPage = new ContactPage(threadPage.get());
        contactPage.open();
    }

    @Test
    @DisplayName("Verify Page Title")
    @Description("Checks if the correct title is displayed upon loading the page")
    void checkPageTitleTest(){
        assertThat(contactPage.pageTitle).containsText(contactPage.pageTitleText);
    }

    @Test
    @DisplayName("Verify File Upload Positive Case")
    @Description("Verify the file upload system only accepts .txt files")
    void verifyFileUploadPositiveTest() throws URISyntaxException {
        getPage().setInputFiles(contactPage.attachmentLabel, FileUtils.getFile("valid.txt"));
        String uploadedFile = contactPage.fileUpload.inputValue();

        Assertions.assertThat(uploadedFile).endsWith("valid.txt");
    }

    @Test
    @DisplayName("Verify File Upload Negative Case")
    @Description("Verify the file upload system doesn't accept other file types")
    void verifyFileUploadNegativeCaseTest() throws URISyntaxException {
        getPage().setInputFiles(contactPage.attachmentLabel, FileUtils.getFile("invalid.css"));

        Assertions.assertThat(contactPage.attachmentError.textContent()).contains("File should be empty.");
    }
}