// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.MessageRole;
import com.azure.ai.openai.assistants.models.PageableList;
import com.azure.ai.openai.assistants.models.ThreadMessage;
import com.azure.ai.openai.assistants.models.ThreadMessageOptions;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.binarydata.BinaryData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AzureMessageSyncTest extends AssistantsClientTestBase {
    private AssistantsClient client;

    @Test
    public void messageOperationCreateRetrieveUpdate() throws IOException {
        client = getAssistantsClient();
        String threadId = createThread(client);
        createMessageRunner(message -> {
            // Create a message
            ThreadMessage threadMessage = null;
            try {
                threadMessage = client.createMessage(threadId, new ThreadMessageOptions(MessageRole.USER, message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(threadMessage, threadId);
            // Retrieve the message
            String threadMessageId = threadMessage.getId();
            ThreadMessage messageRetrieved = null;
            try {
                messageRetrieved = client.getMessage(threadId, threadMessageId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(messageRetrieved, threadId);
            // Update the message
            Map<String, String> metadataUpdate = new HashMap<>();
            metadataUpdate.put("role", MessageRole.ASSISTANT.toString());
            metadataUpdate.put("content", message + " Message Updated");
            ThreadMessage updatedMessage = null;
            try {
                updatedMessage = client.updateMessage(threadId, threadMessageId, metadataUpdate);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(updatedMessage, threadId);
            Map<String, String> metaDataResponse = updatedMessage.getMetadata();
            assertEquals(2, metaDataResponse.size());
            assertTrue(metaDataResponse.containsKey("role"));
            assertTrue(metaDataResponse.containsKey("content"));
            assertEquals(metadataUpdate.get("role"), metaDataResponse.get("role"));
            assertEquals(metadataUpdate.get("content"), metaDataResponse.get("content"));
        });
        // Delete the created thread
        deleteThread(client, threadId);
    }

    @Test
    public void messageResponseOperationCreateRetrieveUpdate() throws IOException {
        client = getAssistantsClient();
        String threadId = createThread(client);
        createMessageRunner(message -> {
            // Create a message
            Map<String, String> metadata = new HashMap<>();
            metadata.put("role", MessageRole.USER.toString());
            metadata.put("content", message);
            BinaryData request = BinaryData.fromObject(metadata);
            Response<BinaryData> response = client.createMessageWithResponse(threadId, request, new RequestOptions());
            ThreadMessage threadMessage = null;
            try {
                threadMessage = assertAndGetValueFromResponse(response, ThreadMessage.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(threadMessage, threadId);
            // Retrieve the message
            String threadMessageId = threadMessage.getId();
            Response<BinaryData> retrievedMessageResponse = client.getMessageWithResponse(threadId, threadMessageId, new RequestOptions());
            ThreadMessage messageRetrieved = null;
            try {
                messageRetrieved = assertAndGetValueFromResponse(retrievedMessageResponse, ThreadMessage.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(messageRetrieved, threadId);
            // Update the message
            Map<String, String> metadataUpdate = new HashMap<>();
            metadataUpdate.put("role", MessageRole.ASSISTANT.toString());
            metadataUpdate.put("content", message + " Message Updated");
            Map<String, Object> requestObj = new HashMap<>();
            requestObj.put("metadata", metadataUpdate);
            BinaryData requestUpdate = BinaryData.fromObject(requestObj);
            Response<BinaryData> updatedMessageResponse = client.updateMessageWithResponse(threadId, threadMessageId, requestUpdate, new RequestOptions());
            ThreadMessage updatedMessage = null;
            try {
                updatedMessage = assertAndGetValueFromResponse(updatedMessageResponse, ThreadMessage.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(updatedMessage, threadId);
            Map<String, String> metaDataResponse = updatedMessage.getMetadata();
            assertEquals(2, metaDataResponse.size());
            assertTrue(metaDataResponse.containsKey("role"));
            assertTrue(metaDataResponse.containsKey("content"));
            assertEquals(metadataUpdate.get("role"), metaDataResponse.get("role"));
            assertEquals(metadataUpdate.get("content"), metaDataResponse.get("content"));
        });
        // Delete the created thread
        deleteThread(client, threadId);
    }

    @Test
    public void listMessages() throws IOException {
        client = getAssistantsClient();
        String threadId = createThread(client);
        createMessageRunner(message -> {
            // Create two messages in user role
            ThreadMessage threadMessage = null;
            try {
                threadMessage = client.createMessage(threadId, new ThreadMessageOptions(MessageRole.USER, message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(threadMessage, threadId);
            ThreadMessage threadMessage2 = null;
            try {
                threadMessage2 =
                    client.createMessage(threadId, new ThreadMessageOptions(MessageRole.USER, message + "second message"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(threadMessage2, threadId);
            // List messages
            PageableList<ThreadMessage> listedMessages = null;
            try {
                listedMessages = client.listMessages(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(listedMessages);
            assertNotNull(listedMessages.getData());
            assertEquals(2, listedMessages.getData().size());
            // List messages with response
            Response<BinaryData> listedMessagesResponse = client.listMessagesWithResponse(threadId, new RequestOptions());
            PageableList<ThreadMessage> listedMessagesWithResponse = asserAndGetPageableListFromResponse(
                listedMessagesResponse, 200, reader -> reader.readArray(ThreadMessage::fromJson));
            assertNotNull(listedMessagesWithResponse);
            assertNotNull(listedMessagesWithResponse.getData());
            assertEquals(2, listedMessagesWithResponse.getData().size());
        });
        // Delete the created thread
        deleteThread(client, threadId);
    }
}
