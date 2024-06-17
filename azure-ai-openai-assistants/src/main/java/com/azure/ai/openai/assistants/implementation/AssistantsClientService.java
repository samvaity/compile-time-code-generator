package com.azure.ai.openai.assistants.implementation;

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
 * The interface defining all the services for AssistantsClient to be used by the proxy service to perform REST calls.
 */
@ServiceInterface(name = "AssistantsClient", host = "{endpoint}")
public interface AssistantsClientService {

    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    @HttpRequestInformation(method = HttpMethod.POST, path = "/assistants", expectedStatusCodes = {200})
    Response<BinaryData> createAssistantSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData assistantCreationOptions,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/assistants", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listAssistantsSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/assistants/{assistantId}", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getAssistantSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("assistantId") String assistantId, @HeaderParam("accept") String accept,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/assistants/{assistantId}", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> updateAssistantSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("assistantId") String assistantId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData updateAssistantOptions, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.DELETE, path = "/assistants/{assistantId}", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> deleteAssistantSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("assistantId") String assistantId, @HeaderParam("accept") String accept,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createThreadSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData assistantThreadCreationOptions, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getThreadSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> updateThreadSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData updateAssistantThreadOptions, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.DELETE, path = "/threads/{threadId}", expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> deleteThreadSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}/messages", expectedStatusCodes =
        {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createMessageSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData threadMessageOptions, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}/messages", expectedStatusCodes =
        {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listMessagesSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}/messages/{messageId}", expectedStatusCodes =
        {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getMessageSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("messageId") String messageId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);


    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}/messages/{messageId}",
        expectedStatusCodes =
            {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> updateMessageSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("messageId") String messageId,
        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData request,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}/runs",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createRunSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData createRunOptions, RequestOptions requestOptions, Context context);


    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}/runs",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listRunsSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}/runs/{runId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getRunSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion, @PathParam("threadId") String threadId,
        @PathParam("runId") String runId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);


    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}/runs/{runId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> updateRunSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("runId") String runId,
        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData request,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}/runs/{runId}/submit_tool_outputs",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> submitToolOutputsToRunSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("runId") String runId,
        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData request,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/{threadId}/runs/{runId}/cancel",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> cancelRunSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("runId") String runId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/threads/runs",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createThreadAndRunSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData createAndRunThreadOptions,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}/runs/{runId}/steps/{stepId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getRunStepSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("runId") String runId,
        @PathParam("stepId") String stepId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/threads/{threadId}/runs/{runId}/steps",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listRunStepsSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("threadId") String threadId, @PathParam("runId") String runId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/files",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listFilesSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion, @HeaderParam("accept") String accept,
        RequestOptions requestOptions, Context context);

    // @Multipart not supported by RestProxy
    @HttpRequestInformation(method = HttpMethod.POST, path = "/files",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> uploadFileSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("content-type") String contentType, @HeaderParam("accept") String accept,
        @BodyParam("multipart/form-data") BinaryData request, RequestOptions requestOptions, Context context);


    @HttpRequestInformation(method = HttpMethod.DELETE, path = "/files/{fileId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> deleteFileSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,  @PathParam("fileId") String fileId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/files/{fileId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getFileSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion, @PathParam("fileId") String fileId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/files/{fileId}/content",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getFileContentSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("fileId") String fileId, @HeaderParam("accept") String accept, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/vector_stores",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listVectorStoresSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/vector_stores",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createVectorStoreSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @HeaderParam("accept") String accept, @BodyParam("application/json") BinaryData vectorStoreOptions,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/vector_stores/{vectorStoreId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getVectorStoreSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @HeaderParam("accept") String accept,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/vector_stores/{vectorStoreId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> modifyVectorStoreSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData vectorStoreUpdateOptions, RequestOptions requestOptions,
        Context context);

    @HttpRequestInformation(method = HttpMethod.DELETE, path = "/vector_stores/{vectorStoreId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> deleteVectorStoreSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @HeaderParam("accept") String accept,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/vector_stores/{vectorStoreId}/files",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listVectorStoreFilesSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @HeaderParam("accept") String accept,
        RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/vector_stores/{vectorStoreId}/files",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createVectorStoreFileSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData request, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/vector_stores/{vectorStoreId}/files/{fileId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getVectorStoreFileSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @PathParam("fileId") String fileId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.DELETE, path = "/vector_stores/{vectorStoreId}/files/{fileId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> deleteVectorStoreFileSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @PathParam("fileId") String fileId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/vector_stores/{vectorStoreId}/file_batches",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> createVectorStoreFileBatchSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @HeaderParam("accept") String accept,
        @BodyParam("application/json") BinaryData request, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/vector_stores/{vectorStoreId}/file_batches/{batchId}",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> getVectorStoreFileBatchSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @PathParam("batchId") String batchId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.POST, path = "/vector_stores/{vectorStoreId}/file_batches/{batchId}/cancel",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> cancelVectorStoreFileBatchSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @PathParam("batchId") String batchId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);

    @HttpRequestInformation(method = HttpMethod.GET, path = "/vector_stores/{vectorStoreId}/file_batches/{batchId}/files",
        expectedStatusCodes = {200})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "CLIENT_AUTHENTICATION", statusCode = {401})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_NOT_FOUND", statusCode = {404})
    @UnexpectedResponseExceptionDetail(exceptionTypeName = "RESOURCE_MODIFIED", statusCode = {409})
    Response<BinaryData> listVectorStoreFileBatchFilesSync(@HostParam("endpoint") String endpoint,
        @QueryParam("api-version") String apiVersion,
        @PathParam("vectorStoreId") String vectorStoreId, @PathParam("batchId") String batchId,
        @HeaderParam("accept") String accept, RequestOptions requestOptions, Context context);
}