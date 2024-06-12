package com.service.clientlibrary;

import com.service.clientlibrary.implementation.AppConfigServiceImpl;
import com.service.clientlibrary.models.AppConfigServiceVersion;
import com.service.clientlibrary.models.ConfigurationSetting;
import com.service.clientlibrary.models.KeyValue;
import io.clientcore.core.http.models.ContentType;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.ClientLogger;
import io.clientcore.core.util.Context;
import io.clientcore.core.util.binarydata.BinaryData;

import java.io.IOException;
import java.time.OffsetDateTime;

public class AppConfigClient {
    private static final ClientLogger LOGGER = new ClientLogger(AppConfigClient.class);
    private final AppConfigServiceImpl appConfigService;

    public AppConfigClient(AppConfigServiceImpl AppConfigClientService) {
        this.appConfigService = AppConfigClientService;
    }

    /**
     * Service version.
     */
    private final AppConfigServiceVersion serviceVersion = AppConfigServiceVersion.V2023_10_01;

    public Response<ConfigurationSetting> getConfigurationSettingWithResponse(ConfigurationSetting setting,
                                                                              OffsetDateTime acceptDateTime, boolean ifChanged, Context context) {
        final String accept = "application/vnd.microsoft.appconfig.kv+json, application/problem+json";
        Response<BinaryData> response = appConfigService.getKeyValueSync(setting.getKey(),
                setting.getLabel(),
                null,
                ContentType.APPLICATION_JSON,
                acceptDateTime == null ? null : acceptDateTime.toString(),
                getETag(ifChanged, setting),
                null,
                null,
                accept,
                null,
                context);
        try {
            return Response.create(response.getRequest(), response.getStatusCode(), response.getHeaders(), response.getValue().toObject(ConfigurationSetting.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Response<ConfigurationSetting> setConfigurationSettingWithResponse(ConfigurationSetting setting,
                                                                              boolean ifUnchanged, Context context) {
        final String accept = "application/vnd.microsoft.appconfig.kv+json, application/problem+json";
        Response<BinaryData> response = appConfigService.putKeyValueSync(setting.getKey(), setting.getLabel(), null, ContentType.APPLICATION_JSON, getETag(ifUnchanged, setting), null,
                toKeyValue(setting), accept, null, context);
        final BinaryData responseBody = response.getBody();
        System.out.println(responseBody);
        try {
            return Response.create(response.getRequest(), response.getStatusCode(), response.getHeaders(), responseBody.toObject(ConfigurationSetting.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static KeyValue toKeyValue(ConfigurationSetting setting) {
        return new KeyValue()
                .setKey(setting.getKey())
                .setValue(setting.getValue())
                .setLabel(setting.getLabel())
                .setContentType(setting.getContentType())
                .setEtag(setting.getETag())
                .setLastModified(setting.getLastModified())
                .setLocked(setting.isReadOnly())
                .setTags(setting.getTags());
    }

    /*
     * Get HTTP header value, if-match or if-none-match.. Used to perform an operation only if the targeted resource's
     * etag matches the value provided.
     */
    private static String getETag(boolean isETagRequired, ConfigurationSetting setting) {
        return isETagRequired ? getETagValue(setting.getETag()) : null;
    }

    private static String getETagValue(String etag) {
        return (etag == null || "*".equals(etag)) ? etag : "\"" + etag + "\"";
    }
}