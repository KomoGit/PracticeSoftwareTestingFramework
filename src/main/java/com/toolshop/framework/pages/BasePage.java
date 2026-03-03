package com.toolshop.framework.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public abstract class BasePage {
    protected Page page;

    public BasePage(Page page){
        this.page = page;
    }

    public final void navigateTo(String url){
        page.navigate(url);
    }

    public void waitForPageLoaded(){
        page.waitForLoadState(LoadState.NETWORKIDLE);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    public final String getPageTitle(){
        return page.title();
    }
}
