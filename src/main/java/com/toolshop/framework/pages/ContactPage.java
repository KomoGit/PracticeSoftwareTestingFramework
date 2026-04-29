package com.toolshop.framework.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ContactPage extends BasePage{

    public final Locator pageTitle;
    public final Locator firstNameInput;
    public final Locator lastNameInput;
    public final Locator emailInput;
    public final Locator fileUpload;
    public final Locator attachmentError;
    public final Locator subjectInput;
    public final Locator messageInput;
    public final String pageTitleText = "Contact";
    public final String attachmentLabel = "#attachment";

    public ContactPage(Page page) {
        super(page);

        this.pageTitle = page.getByTitle("Contact");
        this.firstNameInput = page.getByTestId("first_name");
        this.lastNameInput = page.getByTestId("last_name");
        this.emailInput = page.getByTestId("email");
        this.fileUpload = page.getByTestId("attachment");
        this.attachmentError = page.getByTestId("attachment-error");
        this.subjectInput = page.getByTestId("subject");
        this.messageInput = page.getByTestId("message");
    }

    public void open() {
        String url = "/contact";
        page.navigate(url);
    }

    public ContactPage fillFirstNameInput(String firstName) {
        this.firstNameInput.fill(firstName);
        return this;
    }

    public ContactPage fillLastNameInput(String lastName) {
        this.lastNameInput.fill(lastName);
        return this;
    }

    public ContactPage fillEmailInput(String email) {
        this.emailInput.fill(email);
        return this;
    }

    public ContactPage fillMessageField(String message) {
        this.messageInput.fill(message);
        return this;
    }

    public ContactPage selectSubject(String subject) {
        this.subjectInput.selectOption(subject);
        return this;
    }

    public String getAttachmentError(){
        return attachmentError.textContent();
    }
}
