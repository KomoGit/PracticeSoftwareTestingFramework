package com.toolshop.framework.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.toolshop.framework.constants.ConfigConstants;
import com.toolshop.framework.pages.components.HeaderComponent;

public abstract class BasePage {
    protected Page page;
    protected HeaderComponent headerComponent;

    public BasePage(Page page){

        this.page = page;
        this.headerComponent = new HeaderComponent(page);
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
