package com.toolshop.framework.models;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FrameworkConfigModel {
    public String browser;
    public String baseUrl;
    public String keyVaultUrl;
    public boolean isHeadless;
    public boolean screenshotOnFailure;
    public int timeout;
}