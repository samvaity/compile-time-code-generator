package com.service.clientlibrary.implementation;

import io.clientcore.core.annotation.ServiceInterface;
import io.clientcore.core.http.annotation.BodyParam;
import io.clientcore.core.http.annotation.HeaderParam;
import io.clientcore.core.http.annotation.HostParam;
import io.clientcore.core.http.annotation.HttpRequestInformation;
import io.clientcore.core.http.annotation.PathParam;
import io.clientcore.core.http.annotation.UnexpectedResponseExceptionDetail;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.http.pipeline.HttpPipeline;
import io.clientcore.core.util.binarydata.BinaryData;

import java.lang.reflect.InvocationTargetException;

/**
 * The interface defining all the services for TodoClientTodoItemsAttachments to be used by the proxy service to
 * perform REST calls.
 */
@ServiceInterface(name = "TodoClientTodoItemsAttachments", host = "{endpoint}")
public interface TodoItemsAttachmentsService {

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

    @HttpRequestInformation(
            method = HttpMethod.GET,
            path = "/items/{itemId}/attachments",
            expectedStatusCodes = {200, 404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    @UnexpectedResponseExceptionDetail
    Response<BinaryData> listSync(@io.clientcore.core.http.annotation.HostParam("endpoint") String endpoint, @io.clientcore.core.http.annotation.PathParam("itemId") long itemId,
                                  @io.clientcore.core.http.annotation.HeaderParam("accept") String accept, RequestOptions requestOptions);

    @HttpRequestInformation(
            method = HttpMethod.POST,
            path = "/items/{itemId}/attachments",
            expectedStatusCodes = {204, 404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    @UnexpectedResponseExceptionDetail
    Response<Void> createAttachmentSync(@HostParam("endpoint") String endpoint, @PathParam("itemId") long itemId,
                                        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData contents,
                                        RequestOptions requestOptions);
}


