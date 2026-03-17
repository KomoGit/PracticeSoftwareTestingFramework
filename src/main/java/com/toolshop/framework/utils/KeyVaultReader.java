package com.toolshop.framework.utils;

import com.azure.core.exception.AzureException;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Elnur Abaslı
 */
public class KeyVaultReader {
    private final SecretClient secretClient;

    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    }

    public KeyVaultReader(String vaultUrl) {
        this.secretClient = new SecretClientBuilder()
                .vaultUrl(vaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

    }

    /**
     * Method to return secret value
     * @param secretName The key in Azure
     */
    public String getSecretValue(String secretName) {
        String secretValue;

        try {
            secretValue = secretClient.getSecret(secretName).getValue();
        } catch (Exception e) {
            throw new AzureException(String.format("Azure Key Vault failed to find secret: [%s]", secretName), e);
        }

        return secretValue;
    }
}