package com.service.clientlibrary.implementation;

import com.service.clientlibrary.models.KeyValue;
import io.clientcore.core.annotation.ServiceInterface;
import io.clientcore.core.http.annotation.BodyParam;
import io.clientcore.core.http.annotation.HeaderParam;
import io.clientcore.core.http.annotation.HostParam;
import io.clientcore.core.http.annotation.HttpRequestInformation;
import io.clientcore.core.http.annotation.PathParam;
import io.clientcore.core.http.annotation.QueryParam;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.Context;
import io.clientcore.core.util.binarydata.BinaryData;

/**
 * The interface defining all the services for OpenAIClient to be used by the proxy service to perform REST calls.
 */
@ServiceInterface(name = "AzureAppConfiguration", host = "{endpoint}")
public interface AppConfigService {

    @HttpRequestInformation(path = "/kv/{key}", method = HttpMethod.GET, expectedStatusCodes = {200})
    Response<BinaryData> getKeyValueSync(
        @HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("key") String key,
        @QueryParam("label") String label,
        @HeaderParam("Sync-Token") String syncToken,
        @HeaderParam("content-type") String contentType,
        @HeaderParam("Accept-Datetime") String acceptDatetime,
        @HeaderParam("If-Match") String ifMatch,
        @HeaderParam("If-None-Match") String ifNoneMatch,
        @QueryParam("$Select") String select,
        @HeaderParam("Accept") String accept,
        RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(path = "/kv/{key}", method = HttpMethod.PUT, expectedStatusCodes = {200})
    Response<BinaryData> putKeyValueSync(
        @HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("key") String key,
        @QueryParam("label") String label,
        @HeaderParam("Sync-Token") String syncToken,
        @HeaderParam("content-type") String contentType,
        @HeaderParam("If-Match") String ifMatch,
        @HeaderParam("If-None-Match") String ifNoneMatch,
        @BodyParam("application/json") KeyValue entity,
        @HeaderParam("Accept") String accept,
        RequestOptions requestOptions,
        Context context);
}
