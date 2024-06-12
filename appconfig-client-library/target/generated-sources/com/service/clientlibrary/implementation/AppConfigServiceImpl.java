package com.service.clientlibrary.implementation;

import com.service.clientlibrary.implementation.models.ServiceVersion;
import com.service.clientlibrary.models.KeyValue;
import io.clientcore.core.http.models.ContentType;
import io.clientcore.core.http.models.HttpHeaderName;
import io.clientcore.core.http.models.HttpHeaders;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.HttpRequest;
import io.clientcore.core.http.models.HttpResponse;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.http.models.ResponseBodyMode;
import io.clientcore.core.http.pipeline.HttpPipeline;
import io.clientcore.core.implementation.http.HttpResponseAccessHelper;
import io.clientcore.core.util.ClientLogger;
import io.clientcore.core.util.Context;
import io.clientcore.core.util.binarydata.BinaryData;
import io.clientcore.core.util.serializer.ObjectSerializer;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class AppConfigServiceImpl implements AppConfigService {
    private static final ClientLogger LOGGER = new ClientLogger(AppConfigServiceImpl.class);

    private static Map<HttpPipeline, AppConfigService> INSTANCE_MAP = new HashMap<>();

    private final HttpPipeline defaultPipeline;

    private final ObjectSerializer serializer;

    private final String endpoint;

    private final ServiceVersion serviceVersion;

    public AppConfigServiceImpl(HttpPipeline defaultPipeline, ObjectSerializer serializer,
            String endpoint, ServiceVersion serviceVersion) {
        this.defaultPipeline = defaultPipeline;
        this.serializer = serializer;
        this.endpoint = endpoint;
        this.serviceVersion = serviceVersion;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public HttpPipeline getPipeline() {
        return defaultPipeline;
    }

    public ServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public Response<BinaryData> getKeyValueSync(String key, String label, String syncToken,
            String contentType, String acceptDatetime, String ifMatch, String ifNoneMatch,
            String select, String accept, RequestOptions requestOptions, Context context) {
        return getKeyValueSync(this.getEndpoint(), this.getServiceVersion().getVersion(), key, label, syncToken, contentType, acceptDatetime, ifMatch, ifNoneMatch, select, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getKeyValueSync(String endpoint, String apiVersion, String key,
            String label, String syncToken, String contentType, String acceptDatetime,
            String ifMatch, String ifNoneMatch, String select, String accept,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/kv/" + key + "?api-version=" + apiVersion + "&$Select=" + select + "&label=" + label;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.fromString("If-None-Match"), String.valueOf(ifNoneMatch));
        headers.add(HttpHeaderName.fromString("If-Match"), String.valueOf(ifMatch));
        headers.add(HttpHeaderName.fromString("Accept"), String.valueOf(accept));
        headers.add(HttpHeaderName.fromString("Sync-Token"), String.valueOf(syncToken));
        headers.add(HttpHeaderName.fromString("content-type"), String.valueOf(contentType));
        headers.add(HttpHeaderName.fromString("Accept-Datetime"), String.valueOf(acceptDatetime));
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        // no body content to set

        // send the request through the pipeline
        Response<?> response = pipeline.send(httpRequest);

        final int responseCode = response.getStatusCode();
        boolean expectedResponse = responseCode == 200;
        if (!expectedResponse) {
            throw new RuntimeException("Unexpected response code: " + responseCode);
        }
        ResponseBodyMode responseBodyMode = null;
        if (requestOptions != null) {
            responseBodyMode = requestOptions.getResponseBodyMode();
        }
        if (responseBodyMode == ResponseBodyMode.DESERIALIZE) {
            BinaryData responseBody = response.getBody();
            HttpResponseAccessHelper.setValue((HttpResponse<?>) response, responseBody);
        } else {
            BinaryData responseBody = response.getBody();
            HttpResponseAccessHelper.setBodyDeserializer((HttpResponse<?>) response, (body) -> responseBody);
        }
        return (Response<BinaryData>) response;
    }

    public Response<BinaryData> putKeyValueSync(String key, String label, String syncToken,
            String contentType, String ifMatch, String ifNoneMatch, KeyValue entity, String accept,
            RequestOptions requestOptions, Context context) {
        return putKeyValueSync(this.getEndpoint(), this.getServiceVersion().getVersion(), key, label, syncToken, contentType, ifMatch, ifNoneMatch, entity, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> putKeyValueSync(String endpoint, String apiVersion, String key,
            String label, String syncToken, String contentType, String ifMatch, String ifNoneMatch,
            KeyValue entity, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/kv/" + key + "?api-version=" + apiVersion + "&label=" + label;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.PUT, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.fromString("If-None-Match"), String.valueOf(ifNoneMatch));
        headers.add(HttpHeaderName.fromString("If-Match"), String.valueOf(ifMatch));
        headers.add(HttpHeaderName.fromString("Accept"), String.valueOf(accept));
        headers.add(HttpHeaderName.fromString("Sync-Token"), String.valueOf(syncToken));
        headers.add(HttpHeaderName.fromString("content-type"), String.valueOf(contentType));
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        httpRequest.setBody(BinaryData.fromObject(entity, serializer));

        // send the request through the pipeline
        Response<?> response = pipeline.send(httpRequest);

        final int responseCode = response.getStatusCode();
        boolean expectedResponse = responseCode == 200;
        if (!expectedResponse) {
            throw new RuntimeException("Unexpected response code: " + responseCode);
        }
        ResponseBodyMode responseBodyMode = null;
        if (requestOptions != null) {
            responseBodyMode = requestOptions.getResponseBodyMode();
        }
        if (responseBodyMode == ResponseBodyMode.DESERIALIZE) {
            BinaryData responseBody = response.getBody();
            HttpResponseAccessHelper.setValue((HttpResponse<?>) response, responseBody);
        } else {
            BinaryData responseBody = response.getBody();
            HttpResponseAccessHelper.setBodyDeserializer((HttpResponse<?>) response, (body) -> responseBody);
        }
        return (Response<BinaryData>) response;
    }
}
