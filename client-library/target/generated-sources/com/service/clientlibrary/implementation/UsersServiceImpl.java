package com.service.clientlibrary.implementation;

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
import io.clientcore.core.implementation.http.rest.RestProxyUtils;
import io.clientcore.core.util.ClientLogger;
import io.clientcore.core.util.binarydata.BinaryData;
import io.clientcore.core.util.serializer.ObjectSerializer;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class UsersServiceImpl implements UsersService {
    private static final ClientLogger LOGGER = new ClientLogger(UsersServiceImpl.class);

    private static Map<HttpPipeline, UsersService> INSTANCE_MAP = new HashMap<>();

    private final HttpPipeline defaultPipeline;

    private final ObjectSerializer serializer = RestProxyUtils.createDefaultSerializer();

    private UsersServiceImpl(HttpPipeline defaultPipeline) {
        this.defaultPipeline = defaultPipeline;
    }

    public static UsersService getInstance(HttpPipeline defaultPipeline) {
        return INSTANCE_MAP.computeIfAbsent(defaultPipeline, pipeline -> new UsersServiceImpl(defaultPipeline));
    }

    @Override
    public Response<BinaryData> createSync(String endpoint, String accept, BinaryData user,
            RequestOptions requestOptions) {
        return createSync(defaultPipeline, endpoint, accept, user, requestOptions);
    }

    private Response<BinaryData> createSync(HttpPipeline pipeline, String endpoint, String accept,
            BinaryData user, RequestOptions requestOptions) {
        String host = endpoint + "/users";

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.fromString("accept"), String.valueOf(accept));
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.setBody(BinaryData.fromObject(user, serializer));

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
