package com.azure.ai.openai.assistants.implementation;

import com.azure.ai.openai.assistants.AssistantsServiceVersion;
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

public class AssistantsClientServiceImpl implements AssistantsClientService {
    private static final ClientLogger LOGGER = new ClientLogger(AssistantsClientServiceImpl.class);

    private final HttpPipeline defaultPipeline;

    private final ObjectSerializer serializer;

    private final String endpoint;

    private final AssistantsServiceVersion serviceVersion;

    private String apiVersion;

    public AssistantsClientServiceImpl(HttpPipeline defaultPipeline, ObjectSerializer serializer,
            String endpoint, AssistantsServiceVersion serviceVersion) {
        this.defaultPipeline = defaultPipeline;
        this.serializer = serializer;
        this.endpoint = endpoint;
        this.apiVersion = serviceVersion.getVersion();
        this.serviceVersion = serviceVersion;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public HttpPipeline getPipeline() {
        return defaultPipeline;
    }

    public AssistantsServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public Response<BinaryData> createAssistantSync(String accept,
            BinaryData assistantCreationOptions, RequestOptions requestOptions, Context context) {
        return createAssistantSync(endpoint, apiVersion, accept, assistantCreationOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createAssistantSync(String endpoint, String apiVersion,
            String accept, BinaryData assistantCreationOptions, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/assistants?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) assistantCreationOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> listAssistantsSync(String accept, RequestOptions requestOptions,
            Context context) {
        return listAssistantsSync(endpoint, apiVersion, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listAssistantsSync(String endpoint, String apiVersion,
            String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/assistants?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> getAssistantSync(String assistantId, String accept,
            RequestOptions requestOptions, Context context) {
        return getAssistantSync(endpoint, apiVersion, assistantId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getAssistantSync(String endpoint, String apiVersion,
            String assistantId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/assistants/" + assistantId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> updateAssistantSync(String assistantId, String accept,
            BinaryData updateAssistantOptions, RequestOptions requestOptions, Context context) {
        return updateAssistantSync(endpoint, apiVersion, assistantId, accept, updateAssistantOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> updateAssistantSync(String endpoint, String apiVersion,
            String assistantId, String accept, BinaryData updateAssistantOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/assistants/" + assistantId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) updateAssistantOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> deleteAssistantSync(String assistantId, String accept,
            RequestOptions requestOptions, Context context) {
        return deleteAssistantSync(endpoint, apiVersion, assistantId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> deleteAssistantSync(String endpoint, String apiVersion,
            String assistantId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/assistants/" + assistantId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.DELETE, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> createThreadSync(String accept,
            BinaryData assistantThreadCreationOptions, RequestOptions requestOptions,
            Context context) {
        return createThreadSync(endpoint, apiVersion, accept, assistantThreadCreationOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createThreadSync(String endpoint, String apiVersion, String accept,
            BinaryData assistantThreadCreationOptions, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) assistantThreadCreationOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> getThreadSync(String threadId, String accept,
            RequestOptions requestOptions, Context context) {
        return getThreadSync(endpoint, apiVersion, threadId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getThreadSync(String endpoint, String apiVersion, String threadId,
            String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> updateThreadSync(String threadId, String accept,
            BinaryData updateAssistantThreadOptions, RequestOptions requestOptions,
            Context context) {
        return updateThreadSync(endpoint, apiVersion, threadId, accept, updateAssistantThreadOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> updateThreadSync(String endpoint, String apiVersion,
            String threadId, String accept, BinaryData updateAssistantThreadOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) updateAssistantThreadOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> deleteThreadSync(String threadId, String accept,
            RequestOptions requestOptions, Context context) {
        return deleteThreadSync(endpoint, apiVersion, threadId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> deleteThreadSync(String endpoint, String apiVersion,
            String threadId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.DELETE, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> createMessageSync(String threadId, String accept,
            BinaryData threadMessageOptions, RequestOptions requestOptions, Context context) {
        return createMessageSync(endpoint, apiVersion, threadId, accept, threadMessageOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createMessageSync(String endpoint, String apiVersion,
            String threadId, String accept, BinaryData threadMessageOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/messages?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) threadMessageOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> listMessagesSync(String threadId, String accept,
            RequestOptions requestOptions, Context context) {
        return listMessagesSync(endpoint, apiVersion, threadId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listMessagesSync(String endpoint, String apiVersion,
            String threadId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/messages?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> getMessageSync(String threadId, String messageId, String accept,
            RequestOptions requestOptions, Context context) {
        return getMessageSync(endpoint, apiVersion, threadId, messageId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getMessageSync(String endpoint, String apiVersion, String threadId,
            String messageId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/messages/" + messageId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> updateMessageSync(String threadId, String messageId, String accept,
            BinaryData request, RequestOptions requestOptions, Context context) {
        return updateMessageSync(endpoint, apiVersion, threadId, messageId, accept, request, requestOptions, context);
    }

    @Override
    public Response<BinaryData> updateMessageSync(String endpoint, String apiVersion,
            String threadId, String messageId, String accept, BinaryData request,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/messages/" + messageId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) request;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> createRunSync(String threadId, String accept,
            BinaryData createRunOptions, RequestOptions requestOptions, Context context) {
        return createRunSync(endpoint, apiVersion, threadId, accept, createRunOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createRunSync(String endpoint, String apiVersion, String threadId,
            String accept, BinaryData createRunOptions, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) createRunOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> listRunsSync(String threadId, String accept,
            RequestOptions requestOptions, Context context) {
        return listRunsSync(endpoint, apiVersion, threadId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listRunsSync(String endpoint, String apiVersion, String threadId,
            String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> getRunSync(String threadId, String runId, String accept,
            RequestOptions requestOptions, Context context) {
        return getRunSync(endpoint, apiVersion, threadId, runId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getRunSync(String endpoint, String apiVersion, String threadId,
            String runId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs/" + runId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> updateRunSync(String threadId, String runId, String accept,
            BinaryData request, RequestOptions requestOptions, Context context) {
        return updateRunSync(endpoint, apiVersion, threadId, runId, accept, request, requestOptions, context);
    }

    @Override
    public Response<BinaryData> updateRunSync(String endpoint, String apiVersion, String threadId,
            String runId, String accept, BinaryData request, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs/" + runId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) request;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> submitToolOutputsToRunSync(String threadId, String runId,
            String accept, BinaryData request, RequestOptions requestOptions, Context context) {
        return submitToolOutputsToRunSync(endpoint, apiVersion, threadId, runId, accept, request, requestOptions, context);
    }

    @Override
    public Response<BinaryData> submitToolOutputsToRunSync(String endpoint, String apiVersion,
            String threadId, String runId, String accept, BinaryData request,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs/" + runId + "/submit_tool_outputs?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) request;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> cancelRunSync(String threadId, String runId, String accept,
            RequestOptions requestOptions, Context context) {
        return cancelRunSync(endpoint, apiVersion, threadId, runId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> cancelRunSync(String endpoint, String apiVersion, String threadId,
            String runId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs/" + runId + "/cancel?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> createThreadAndRunSync(String accept,
            BinaryData createAndRunThreadOptions, RequestOptions requestOptions, Context context) {
        return createThreadAndRunSync(endpoint, apiVersion, accept, createAndRunThreadOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createThreadAndRunSync(String endpoint, String apiVersion,
            String accept, BinaryData createAndRunThreadOptions, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/runs?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) createAndRunThreadOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> getRunStepSync(String threadId, String runId, String stepId,
            String accept, RequestOptions requestOptions, Context context) {
        return getRunStepSync(endpoint, apiVersion, threadId, runId, stepId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getRunStepSync(String endpoint, String apiVersion, String threadId,
            String runId, String stepId, String accept, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs/" + runId + "/steps/" + stepId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> listRunStepsSync(String threadId, String runId, String accept,
            RequestOptions requestOptions, Context context) {
        return listRunStepsSync(endpoint, apiVersion, threadId, runId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listRunStepsSync(String endpoint, String apiVersion,
            String threadId, String runId, String accept, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/threads/" + threadId + "/runs/" + runId + "/steps?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> listFilesSync(String accept, RequestOptions requestOptions,
            Context context) {
        return listFilesSync(endpoint, apiVersion, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listFilesSync(String endpoint, String apiVersion, String accept,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/files?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> uploadFileSync(String contentType, String accept,
            BinaryData request, RequestOptions requestOptions, Context context) {
        return uploadFileSync(endpoint, apiVersion, contentType, accept, request, requestOptions, context);
    }

    @Override
    public Response<BinaryData> uploadFileSync(String endpoint, String apiVersion,
            String contentType, String accept, BinaryData request, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/files?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.CONTENT_TYPE, contentType);
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        BinaryData binaryData = (BinaryData) request;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> deleteFileSync(String fileId, String accept,
            RequestOptions requestOptions, Context context) {
        return deleteFileSync(endpoint, apiVersion, fileId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> deleteFileSync(String endpoint, String apiVersion, String fileId,
            String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/files/" + fileId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.DELETE, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> getFileSync(String fileId, String accept,
            RequestOptions requestOptions, Context context) {
        return getFileSync(endpoint, apiVersion, fileId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getFileSync(String endpoint, String apiVersion, String fileId,
            String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/files/" + fileId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> getFileContentSync(String fileId, String accept,
            RequestOptions requestOptions, Context context) {
        return getFileContentSync(endpoint, apiVersion, fileId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getFileContentSync(String endpoint, String apiVersion,
            String fileId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/files/" + fileId + "/content?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> listVectorStoresSync(String accept, RequestOptions requestOptions,
            Context context) {
        return listVectorStoresSync(endpoint, apiVersion, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listVectorStoresSync(String endpoint, String apiVersion,
            String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> createVectorStoreSync(String accept, BinaryData vectorStoreOptions,
            RequestOptions requestOptions, Context context) {
        return createVectorStoreSync(endpoint, apiVersion, accept, vectorStoreOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createVectorStoreSync(String endpoint, String apiVersion,
            String accept, BinaryData vectorStoreOptions, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) vectorStoreOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> getVectorStoreSync(String vectorStoreId, String accept,
            RequestOptions requestOptions, Context context) {
        return getVectorStoreSync(endpoint, apiVersion, vectorStoreId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getVectorStoreSync(String endpoint, String apiVersion,
            String vectorStoreId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> modifyVectorStoreSync(String vectorStoreId, String accept,
            BinaryData vectorStoreUpdateOptions, RequestOptions requestOptions, Context context) {
        return modifyVectorStoreSync(endpoint, apiVersion, vectorStoreId, accept, vectorStoreUpdateOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> modifyVectorStoreSync(String endpoint, String apiVersion,
            String vectorStoreId, String accept, BinaryData vectorStoreUpdateOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) vectorStoreUpdateOptions;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> deleteVectorStoreSync(String vectorStoreId, String accept,
            RequestOptions requestOptions, Context context) {
        return deleteVectorStoreSync(endpoint, apiVersion, vectorStoreId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> deleteVectorStoreSync(String endpoint, String apiVersion,
            String vectorStoreId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.DELETE, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> listVectorStoreFilesSync(String vectorStoreId, String accept,
            RequestOptions requestOptions, Context context) {
        return listVectorStoreFilesSync(endpoint, apiVersion, vectorStoreId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listVectorStoreFilesSync(String endpoint, String apiVersion,
            String vectorStoreId, String accept, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/files?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> createVectorStoreFileSync(String vectorStoreId, String accept,
            BinaryData request, RequestOptions requestOptions, Context context) {
        return createVectorStoreFileSync(endpoint, apiVersion, vectorStoreId, accept, request, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createVectorStoreFileSync(String endpoint, String apiVersion,
            String vectorStoreId, String accept, BinaryData request, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/files?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) request;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> getVectorStoreFileSync(String vectorStoreId, String fileId,
            String accept, RequestOptions requestOptions, Context context) {
        return getVectorStoreFileSync(endpoint, apiVersion, vectorStoreId, fileId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getVectorStoreFileSync(String endpoint, String apiVersion,
            String vectorStoreId, String fileId, String accept, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/files/" + fileId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> deleteVectorStoreFileSync(String vectorStoreId, String fileId,
            String accept, RequestOptions requestOptions, Context context) {
        return deleteVectorStoreFileSync(endpoint, apiVersion, vectorStoreId, fileId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> deleteVectorStoreFileSync(String endpoint, String apiVersion,
            String vectorStoreId, String fileId, String accept, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/files/" + fileId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.DELETE, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> createVectorStoreFileBatchSync(String vectorStoreId, String accept,
            BinaryData request, RequestOptions requestOptions, Context context) {
        return createVectorStoreFileBatchSync(endpoint, apiVersion, vectorStoreId, accept, request, requestOptions, context);
    }

    @Override
    public Response<BinaryData> createVectorStoreFileBatchSync(String endpoint, String apiVersion,
            String vectorStoreId, String accept, BinaryData request, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/file_batches?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_TYPE, ContentType.APPLICATION_JSON);
        BinaryData binaryData = (BinaryData) request;
        if (binaryData.getLength() != null) {
            httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, String.valueOf(binaryData.getLength()));
            httpRequest.setBody(binaryData);
        }

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

    public Response<BinaryData> getVectorStoreFileBatchSync(String vectorStoreId, String batchId,
            String accept, RequestOptions requestOptions, Context context) {
        return getVectorStoreFileBatchSync(endpoint, apiVersion, vectorStoreId, batchId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getVectorStoreFileBatchSync(String endpoint, String apiVersion,
            String vectorStoreId, String batchId, String accept, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/file_batches/" + batchId + "?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> cancelVectorStoreFileBatchSync(String vectorStoreId, String batchId,
            String accept, RequestOptions requestOptions, Context context) {
        return cancelVectorStoreFileBatchSync(endpoint, apiVersion, vectorStoreId, batchId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> cancelVectorStoreFileBatchSync(String endpoint, String apiVersion,
            String vectorStoreId, String batchId, String accept, RequestOptions requestOptions,
            Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/file_batches/" + batchId + "/cancel?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.POST, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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

    public Response<BinaryData> listVectorStoreFileBatchFilesSync(String vectorStoreId,
            String batchId, String accept, RequestOptions requestOptions, Context context) {
        return listVectorStoreFileBatchFilesSync(endpoint, apiVersion, vectorStoreId, batchId, accept, requestOptions, context);
    }

    @Override
    public Response<BinaryData> listVectorStoreFileBatchFilesSync(String endpoint,
            String apiVersion, String vectorStoreId, String batchId, String accept,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/vector_stores/" + vectorStoreId + "/file_batches/" + batchId + "/files?api-version=" + apiVersion;

        // create the request
        HttpRequest httpRequest = new HttpRequest(HttpMethod.GET, host);

        // set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaderName.ACCEPT, accept);
        httpRequest.setHeaders(headers);

        // add RequestOptions to the request
        httpRequest.setRequestOptions(requestOptions);

        // set the body content if present
        httpRequest.getHeaders().set(HttpHeaderName.CONTENT_LENGTH, "0");
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
}
