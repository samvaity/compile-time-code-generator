package com.service.clientlibrary;

import com.service.clientlibrary.models.ConfigurationSetting;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.Context;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class AppConfigClientTest {
    private String CONNECTION_STRING = System.getenv("APP_CONFIG_CONNECTION_STRING");

    @Test
    public void testAddSetting() {
        AppConfigClient configurationClient = new AppConfigClientBuilder()
            .connectionString(CONNECTION_STRING)
            .buildClient();
        try (Response<ConfigurationSetting> response =
                 configurationClient.setConfigurationSettingWithResponse(new ConfigurationSetting()
                         .setKey("prodDBConnection")
                         .setLabel("westUS")
                         .setValue("db_connection"),
                     false,
                     Context.none())) {
            ConfigurationSetting setting = response.getValue();
            System.out.printf("Key: %s, Value: %s, Label: %s", setting.getKey(), setting.getValue(), setting.getLabel());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testGetSetting() {
        AppConfigClient configurationClient = new AppConfigClientBuilder()
            .connectionString(CONNECTION_STRING)
            .buildClient();
        ConfigurationSetting setting =
            configurationClient.getConfigurationSettingWithResponse(new ConfigurationSetting()
                .setKey("prodDBConnection")
                .setLabel("westUS"), null, false, Context.none()).getValue();
        System.out.printf("Key: %s, Value: %s", setting.getKey(), setting.getValue());
    }
}