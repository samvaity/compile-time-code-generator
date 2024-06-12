package com.service.clientlibrary;

import com.service.clientlibrary.implementation.OpenAIClientServiceImpl;
import com.service.clientlibrary.implementation.models.MultipartDataHelper;
import com.service.clientlibrary.implementation.models.MultipartDataSerializationResult;
import com.service.clientlibrary.models.AudioTranscription;
import com.service.clientlibrary.models.AudioTranscriptionFormat;
import com.service.clientlibrary.models.AudioTranscriptionOptions;
import com.service.clientlibrary.models.ChatCompletions;
import com.service.clientlibrary.models.ChatCompletionsOptions;
import com.service.clientlibrary.models.OpenAIServiceVersion;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.ClientLogger;
import io.clientcore.core.util.Context;
import io.clientcore.core.util.binarydata.BinaryData;

import javax.annotation.processing.Completions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenAIClient {
    private static final ClientLogger LOGGER = new ClientLogger(OpenAIClient.class);
    private final OpenAIClientServiceImpl openAIClientService;

    public OpenAIClient(OpenAIClientServiceImpl openAIClientService) {
        this.openAIClientService = openAIClientService;
    }

    /**
     * Service version.
     */
    private final OpenAIServiceVersion serviceVersion = OpenAIServiceVersion.V2024_05_01_PREVIEW;

    /**
     * Gets Service version.
     *
     * @return the serviceVersion value.
     */
    public OpenAIServiceVersion getServiceVersion() {
        return this.serviceVersion;
    }

    private Response<BinaryData> getAudioTranscriptionAsPlainTextWithResponse(String deploymentOrModelName,
                                                                             BinaryData audioTranscriptionOptions, RequestOptions requestOptions) {
        final String contentType = "multipart/form-data";
        final String accept = "text/plain, application/json";
        return openAIClientService.getAudioTranscriptionAsPlainTextSync(deploymentOrModelName, contentType, accept, audioTranscriptionOptions, requestOptions, Context.EMPTY);
    }

    public Response<AudioTranscription> getAudioTranscriptionWithResponse(String deploymentOrModelName, String fileName,
                                                                          AudioTranscriptionOptions audioTranscriptionOptions, RequestOptions requestOptions) {
        // checking allowed formats for a JSON response
        validateAudioResponseFormatForTranscription(audioTranscriptionOptions);
        // embedding the `model` in the request for non-Azure case
        if (this.openAIClientService != null) {
            audioTranscriptionOptions.setModel(deploymentOrModelName);
        }
        // setting the name as part of the request object will allow users for better visualization in the web dashboard
            audioTranscriptionOptions.setFilename(fileName);
        final MultipartDataHelper helper = new MultipartDataHelper();
        final MultipartDataSerializationResult result = helper.serializeRequest(audioTranscriptionOptions);
        final BinaryData data = result.getData();
        requestOptions = helper.getRequestOptionsForMultipartFormData(requestOptions, result, helper.getBoundary());
        Response<BinaryData> response = getAudioTranscriptionAsPlainTextWithResponse(deploymentOrModelName, data,
                requestOptions);
        try {
            return Response.create(response.getRequest(), response.getStatusCode(), response.getHeaders(), response.getValue().toObject(AudioTranscription.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void validateAudioResponseFormatForTranscription(AudioTranscriptionOptions audioTranscriptionOptions) {
        List<AudioTranscriptionFormat> acceptedFormats = new ArrayList<>();
        acceptedFormats.add(AudioTranscriptionFormat.JSON);
        acceptedFormats.add(AudioTranscriptionFormat.VERBOSE_JSON);
        AudioTranscriptionFormat responseFormat = audioTranscriptionOptions.getResponseFormat();
        if (!acceptedFormats.contains(responseFormat)) {
            throw LOGGER.logThrowableAsError(new IllegalArgumentException(
                    "This operation does not support the requested audio format: " + responseFormat
                            + ", supported formats: JSON, VERBOSE_JSON."));
        }
    }

    private Response<BinaryData> getCompletionsWithResponse(String deploymentOrModelName, BinaryData completionsOptions,
                                                           RequestOptions requestOptions) {
        final String accept = "application/json";
        return openAIClientService.getChatCompletionsSync(deploymentOrModelName, accept, completionsOptions, requestOptions,
            Context.EMPTY);
    }

    public Response<ChatCompletions> getChatCompletions(String deploymentOrModelName,
                                                        ChatCompletionsOptions chatCompletionsOptions) {
        Response<BinaryData> response = getCompletionsWithResponse(deploymentOrModelName,
                BinaryData.fromObject(chatCompletionsOptions), null);
        try {
            return Response.create(response.getRequest(), response.getStatusCode(), response.getHeaders(), response.getValue().toObject(ChatCompletions.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}