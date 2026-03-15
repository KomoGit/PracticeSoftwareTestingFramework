package com.toolshop.framework.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public abstract class BasePage {
    protected Page page;

    public BasePage(Page page){
        this.page = page;
    }

    public BasePage waitForPageLoaded(){
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        return this;
    }

    public BasePage waitForPageElement(String locator){
        page.waitForSelector(locator);
        return this;
    }

    public final String getPageTitle(){
        return page.title();
    }
}
