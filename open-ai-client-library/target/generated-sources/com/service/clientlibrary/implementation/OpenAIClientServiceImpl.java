package com.service.clientlibrary.implementation;

import com.service.clientlibrary.models.OpenAIServiceVersion;
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

public class OpenAIClientServiceImpl implements OpenAIClientService {
    private static final ClientLogger LOGGER = new ClientLogger(OpenAIClientServiceImpl.class);

    private final HttpPipeline defaultPipeline;

    private final ObjectSerializer serializer;

    private final String endpoint;

    private final OpenAIServiceVersion serviceVersion;

    private String apiVersion;

    public OpenAIClientServiceImpl(HttpPipeline defaultPipeline, ObjectSerializer serializer,
            String endpoint, OpenAIServiceVersion serviceVersion) {
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

    public OpenAIServiceVersion getServiceVersion() {
        return serviceVersion;
    }

    public Response<BinaryData> getAudioTranscriptionAsPlainTextSync(String deploymentOrModelName,
            String contentType, String accept, BinaryData audioTranscriptionOptions,
            RequestOptions requestOptions, Context context) {
        return getAudioTranscriptionAsPlainTextSync(endpoint, apiVersion, deploymentOrModelName, contentType, accept, audioTranscriptionOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getAudioTranscriptionAsPlainTextSync(String endpoint,
            String apiVersion, String deploymentOrModelName, String contentType, String accept,
            BinaryData audioTranscriptionOptions, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/audio/transcriptions?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) audioTranscriptionOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> getAudioTranscriptionAsResponseObjectSync(
            String deploymentOrModelName, String contentType, String accept,
            BinaryData audioTranscriptionOptions, RequestOptions requestOptions, Context context) {
        return getAudioTranscriptionAsResponseObjectSync(endpoint, apiVersion, deploymentOrModelName, contentType, accept, audioTranscriptionOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getAudioTranscriptionAsResponseObjectSync(String endpoint,
            String apiVersion, String deploymentOrModelName, String contentType, String accept,
            BinaryData audioTranscriptionOptions, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/audio/transcriptions?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) audioTranscriptionOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> getAudioTranslationAsPlainTextSync(String deploymentOrModelName,
            String contentType, String accept, BinaryData audioTranslationOptions,
            RequestOptions requestOptions, Context context) {
        return getAudioTranslationAsPlainTextSync(endpoint, apiVersion, deploymentOrModelName, contentType, accept, audioTranslationOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getAudioTranslationAsPlainTextSync(String endpoint,
            String apiVersion, String deploymentOrModelName, String contentType, String accept,
            BinaryData audioTranslationOptions, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/audio/translations?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) audioTranslationOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> getAudioTranslationAsResponseObjectSync(
            String deploymentOrModelName, String contentType, String accept,
            BinaryData audioTranslationOptions, RequestOptions requestOptions, Context context) {
        return getAudioTranslationAsResponseObjectSync(endpoint, apiVersion, deploymentOrModelName, contentType, accept, audioTranslationOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getAudioTranslationAsResponseObjectSync(String endpoint,
            String apiVersion, String deploymentOrModelName, String contentType, String accept,
            BinaryData audioTranslationOptions, RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/audio/translations?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) audioTranslationOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> getChatCompletionsSync(String deploymentOrModelName, String accept,
            BinaryData chatCompletionsOptions, RequestOptions requestOptions, Context context) {
        return getChatCompletionsSync(endpoint, apiVersion, deploymentOrModelName, accept, chatCompletionsOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getChatCompletionsSync(String endpoint, String apiVersion,
            String deploymentOrModelName, String accept, BinaryData chatCompletionsOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/chat/completions?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) chatCompletionsOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> getImageGenerationsSync(String deploymentOrModelName, String accept,
            BinaryData imageGenerationOptions, RequestOptions requestOptions, Context context) {
        return getImageGenerationsSync(endpoint, apiVersion, deploymentOrModelName, accept, imageGenerationOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getImageGenerationsSync(String endpoint, String apiVersion,
            String deploymentOrModelName, String accept, BinaryData imageGenerationOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/images/generations?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) imageGenerationOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> generateSpeechFromTextSync(String deploymentOrModelName,
            String accept, BinaryData speechGenerationOptions, RequestOptions requestOptions,
            Context context) {
        return generateSpeechFromTextSync(endpoint, apiVersion, deploymentOrModelName, accept, speechGenerationOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> generateSpeechFromTextSync(String endpoint, String apiVersion,
            String deploymentOrModelName, String accept, BinaryData speechGenerationOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/audio/speech?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) speechGenerationOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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

    public Response<BinaryData> getEmbeddingsSync(String deploymentOrModelName, String accept,
            BinaryData embeddingsOptions, RequestOptions requestOptions, Context context) {
        return getEmbeddingsSync(endpoint, apiVersion, deploymentOrModelName, accept, embeddingsOptions, requestOptions, context);
    }

    @Override
    public Response<BinaryData> getEmbeddingsSync(String endpoint, String apiVersion,
            String deploymentOrModelName, String accept, BinaryData embeddingsOptions,
            RequestOptions requestOptions, Context context) {
        HttpPipeline pipeline = this.getPipeline();
        String host = endpoint + "/openai/deployments/" + deploymentOrModelName + "/embeddings?api-version=" + apiVersion;

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
        BinaryData binaryData = (BinaryData) embeddingsOptions;
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
        responseBodyMode = ResponseBodyMode.IGNORE;
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
