package com.service.clientlibrary.implementation;

import io.clientcore.core.annotation.ServiceInterface;
import io.clientcore.core.http.annotation.BodyParam;
import io.clientcore.core.http.annotation.HeaderParam;
import io.clientcore.core.http.annotation.HostParam;
import io.clientcore.core.http.annotation.HttpRequestInformation;
import io.clientcore.core.http.annotation.PathParam;
import io.clientcore.core.http.annotation.QueryParam;
import io.clientcore.core.http.annotation.UnexpectedResponseExceptionDetail;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.Context;
import io.clientcore.core.util.binarydata.BinaryData;

/**
 * The interface defining all the services for OpenAIClient to be used by the proxy service to perform REST calls.
 */
@ServiceInterface(name = "OpenAIClient", host = "{endpoint}/openai")
public interface OpenAIClientService {

    // @Multipart not supported by RestProxy
    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/audio/transcriptions", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getAudioTranscriptionAsPlainTextSync(@HostParam("endpoint") String endpoint,
                                                              @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                              @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
                                                              @BodyParam("multipart/form-data") BinaryData audioTranscriptionOptions, RequestOptions requestOptions,
                                                              Context context);


    // @Multipart not supported by RestProxy
    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/audio/transcriptions", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getAudioTranscriptionAsResponseObjectSync(@HostParam("endpoint") String endpoint,
                                                                   @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                                   @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
                                                                   @BodyParam("multipart/form-data") BinaryData audioTranscriptionOptions, RequestOptions requestOptions,
                                                                   Context context);


    // @Multipart not supported by RestProxy
    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/audio/translations", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getAudioTranslationAsPlainTextSync(@HostParam("endpoint") String endpoint,
                                                            @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                            @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
                                                            @BodyParam("multipart/form-data") BinaryData audioTranslationOptions, RequestOptions requestOptions,
                                                            Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/audio/translations", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getAudioTranslationAsResponseObjectSync(@HostParam("endpoint") String endpoint,
                                                                 @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                                 @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
                                                                 @BodyParam("multipart/form-data") BinaryData audioTranslationOptions, RequestOptions requestOptions,
                                                                 Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/chat/completions", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getChatCompletionsSync(@HostParam("endpoint") String endpoint,
                                                @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData chatCompletionsOptions,
                                                RequestOptions requestOptions, Context context);


    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/images/generations", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getImageGenerationsSync(@HostParam("endpoint") String endpoint,
                                                 @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                 @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData imageGenerationOptions,
                                                 RequestOptions requestOptions, Context context);



    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/audio/speech", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> generateSpeechFromTextSync(@HostParam("endpoint") String endpoint,
                                                    @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                                    @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData speechGenerationOptions,
                                                    RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/deployments/{deploymentId}/embeddings", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    Response<BinaryData> getEmbeddingsSync(@HostParam("endpoint") String endpoint,
                                           @QueryParam("api-version") String apiVersion, @PathParam("deploymentId") String deploymentOrModelName,
                                           @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData embeddingsOptions,
                                           RequestOptions requestOptions, Context context);
}
