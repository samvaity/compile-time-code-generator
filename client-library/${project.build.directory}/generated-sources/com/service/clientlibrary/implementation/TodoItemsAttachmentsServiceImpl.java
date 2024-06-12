package com.service.clientlibrary.implementation;

import com.service.clientlibrary.models.ServiceVersion;
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
import io.clientcore.core.util.binarydata.BinaryData;
import io.clientcore.core.util.serializer.ObjectSerializer;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.lang.Void;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TodoItemsAttachmentsServiceImpl implements TodoItemsAttachmentsService {
    private static final ClientLogger LOGGER = new ClientLogger(TodoItemsAttachmentsServiceImpl.class);

    private static Map<HttpPipeline, TodoItemsAttachmentsService> INSTANCE_MAP = new HashMap<>();

    private final HttpPipeline defaultPipeline;

    private final ObjectSerializer serializer;

    private final String endpoint;

    private final ServiceVersion serviceVersion;

    public TodoItemsAttachmentsServiceImpl(HttpPipeline defaultPipeline,
            ObjectSerializer serializer, String endpoint, ServiceVersion serviceVersion) {
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

    public Response<BinaryData> listSync(long itemId, String accept,
            RequestOptions requestOptions) {
        return listSync(this.getEndpoint(), this.getServiceVersion().getVersion(), itemId, accept, requestOptions);
    }

    @Override
    public Response<BinaryData> listSync(String endpoint, long itemId, String accept,
            RequestOptions requestOptions) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/items/" + itemId + "/attachments";

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.fromString("accept"), String.valueOf(accept));
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        // no body content to set

        // send the request through the pipeline
        Response<?> response = pipeline.send(httpRequest);

        final int responseCode = response.getStatusCode();
        boolean expectedResponse = Arrays.binarySearch(new int[] {200, 404}, responseCode) > -1;
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

    public Response<Void> createAttachmentSync(long itemId, String accept, BinaryData contents,
            RequestOptions requestOptions) {
        return createAttachmentSync(this.getEndpoint(), this.getServiceVersion().getVersion(), itemId, accept, contents, requestOptions);
    }

    @Override
    public Response<Void> createAttachmentSync(String endpoint, long itemId, String accept,
            BinaryData contents, RequestOptions requestOptions) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/items/" + itemId + "/attachments";

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.fromString("accept"), String.valueOf(accept));
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.setBody(BinaryData.fromObject(contents, serializer));

        // send the request through the pipeline
        Response<?> response = pipeline.send(httpRequest);

        final int responseCode = response.getStatusCode();
        boolean expectedResponse = Arrays.binarySearch(new int[] {204, 404}, responseCode) > -1;
        if (!expectedResponse) {
            throw new RuntimeException("Unexpected response code: " + responseCode);
        }
        try {
            response.close();
        } catch (IOException e) {
            throw LOGGER.logThrowableAsError(new UncheckedIOException(e));
        }
        return (Response<Void>) response;
    }
}
