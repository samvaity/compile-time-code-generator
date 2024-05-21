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
 * The interface defining all the services for TodoClientTodoItems to be used by the proxy service to perform REST
 * calls.
 */
/**
 * The interface defining all the services for TodoClientTodoItems to be used by the proxy service to perform REST
 * calls.
 */
@ServiceInterface(name = "TodoClientTodoItems", host = "{endpoint}")
public interface TodoItemsService {

    // TODO: edited the following to match GeneratedServiceAPI
    static TodoItemsService getInstance(HttpPipeline pipeline) {
        // TODO enable when appropriate - it's easier to pass a null pipeline in for now
//        if (pipeline == null) {
//            throw new IllegalArgumentException("pipeline cannot be null");
//        }
        try {
            Class<?> clazz = Class.forName("com.service.clientlibrary.implementation.TodoItemsService");
            return (TodoItemsService) clazz.getMethod("getInstance", HttpPipeline.class).invoke(null, pipeline);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @HttpRequestInformation(method = HttpMethod.GET, path = "/items", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    @UnexpectedResponseExceptionDetail
    Response<BinaryData> listSync(@HostParam("endpoint") String endpoint, @HeaderParam("accept") String accept,
                                  RequestOptions requestOptions);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/items", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    @UnexpectedResponseExceptionDetail
    Response<BinaryData> createSync(@HostParam("endpoint") String endpoint,
                                    @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
                                    @BodyParam("application/json") BinaryData request, RequestOptions requestOptions);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/items/{id}", expectedStatusCodes = { 200, 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    @UnexpectedResponseExceptionDetail
    Response<BinaryData> getSync(@HostParam("endpoint") String endpoint, @PathParam("id") long id,
                                 @HeaderParam("accept") String accept, RequestOptions requestOptions);

    @HttpRequestInformation(method = HttpMethod.PATCH, path = "/items/{id}", expectedStatusCodes = { 200 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = { 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    @UnexpectedResponseExceptionDetail
    Response<BinaryData> updateSync(@HostParam("endpoint") String endpoint,
                                    @HeaderParam("content-type") String contentType, @PathParam("id") long id,
                                    @HeaderParam("accept") String accept, @BodyParam("application/merge-patch+json") BinaryData patch,
                                    RequestOptions requestOptions);

    @HttpRequestInformation(method = HttpMethod.DELETE, path = "/items/{id}", expectedStatusCodes = { 204, 404 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = { 401 })
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = { 409 })
    @UnexpectedResponseExceptionDetail
    Response<Void> deleteSync(@HostParam("endpoint") String endpoint, @PathParam("id") long id,
                              @HeaderParam("accept") String accept, RequestOptions requestOptions);
}