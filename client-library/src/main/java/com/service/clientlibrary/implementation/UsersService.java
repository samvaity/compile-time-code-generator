package com.service.clientlibrary.implementation;

import io.clientcore.core.annotation.ServiceInterface;
import io.clientcore.core.http.annotation.BodyParam;
import io.clientcore.core.http.annotation.HeaderParam;
import io.clientcore.core.http.annotation.HostParam;
import io.clientcore.core.http.annotation.HttpRequestInformation;
import io.clientcore.core.http.annotation.UnexpectedResponseExceptionDetail;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.http.pipeline.HttpPipeline;
import io.clientcore.core.util.binarydata.BinaryData;

import java.lang.reflect.InvocationTargetException;

/**
 * The interface defining all the services for TodoClientUsers to be used by the proxy service to perform REST
 * calls.
 */
@ServiceInterface(name = "TodoClientUsers", host = "{endpoint}")
public interface UsersService {

    // TODO: edited the following to match GeneratedServiceAPI
    static TodoItemsAttachmentsService getInstance(HttpPipeline pipeline) {
        // TODO enable when appropriate - it's easier to pass a null pipeline in for now
//        if (pipeline == null) {
//            throw new IllegalArgumentException("pipeline cannot be null");
//        }
        try {
            Class<?> clazz = Class.forName("com.service.clientlibrary.implementation.TodoItemsAttachmentsServiceImpl");
            return (TodoItemsAttachmentsService) clazz.getMethod("getInstance", HttpPipeline.class).invoke(null, pipeline);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @HttpRequestInformation(method = HttpMethod.POST, path = "/users", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    @UnexpectedResponseExceptionDetail
    Response<BinaryData> createSync(@HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept,
                                    @BodyParam("application/json") BinaryData user, RequestOptions requestOptions);
}
