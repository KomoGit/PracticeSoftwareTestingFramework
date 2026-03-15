package com.toolshop.framework.utils;

import com.azure.core.exception.AzureException;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.toolshop.framework.exceptions.FailedToFormatException;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Elnur Abaslı
 */
public class KeyVaultReader {
    private final SecretClient secretClient;
    private final ObjectMapper mapper;

    static {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    }

    public KeyVaultReader(String vaultUrl) {
        this.secretClient = new SecretClientBuilder()
                .vaultUrl(vaultUrl)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        this.mapper = new ObjectMapper(new JsonFactory());
    }

    /**
     * Generic method to fetch a secret and map it to any class
     * @param secretName The key in Azure
     * @param clazz The class type to map to (e.g., UserModel.class)
     */
    public <T> T getSecretAs(String secretName, Class<T> clazz){
        String secretValue;

        try {
            secretValue = secretClient.getSecret(secretName).getValue();
        } catch (Exception e) {
            throw new AzureException(String.format("Azure Key Vault failed to find secret: [%s]", secretName), e);
        }

        try {
            return mapper.readValue(secretValue, clazz);
        } catch (Exception e) {
            throw new FailedToFormatException(String.format("Failed to map JSON secret [%s] to class [%s].",
                    secretName, clazz.getSimpleName()), e);
        }
    }
}