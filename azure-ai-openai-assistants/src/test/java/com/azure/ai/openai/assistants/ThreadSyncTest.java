// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.AssistantThread;
import com.azure.ai.openai.assistants.models.ThreadDeletionStatus;
import com.azure.ai.openai.assistants.models.UpdateAssistantThreadOptions;
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

public class ThreadSyncTest extends AssistantsClientTestBase {
    private AssistantsClient client;

    @Test
    public void threadCRUD() {
        client = getAssistantsClient();
        createRunRunner(threadCreationOptions -> {
            // Create a thread
            AssistantThread assistantThread = null;
            try {
                assistantThread = client.createThread(threadCreationOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = assistantThread.getId();
            assertNotNull(threadId);
            assertNotNull(assistantThread.getCreatedAt());
            assertEquals("thread", assistantThread.getObject());

            // Get a thread
            AssistantThread retrievedThread = null;
            try {
                retrievedThread = client.getThread(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(threadId, retrievedThread.getId());
            assertNotNull(retrievedThread.getCreatedAt());
            assertEquals("thread", retrievedThread.getObject());

            // Update a thread
            Map<String, String> metadata = new HashMap<>();
            metadata.put("role", "user");
            metadata.put("name", "John Doe");
            metadata.put("content", "Hello, I'm John Doe.");
            AssistantThread updatedThread = null;
            try {
                updatedThread =
                    client.updateThread(assistantThread.getId(), new UpdateAssistantThreadOptions().setMetadata(metadata));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(threadId, updatedThread.getId());
            assertEquals("user", updatedThread.getMetadata().get("role"));
            assertEquals("John Doe", updatedThread.getMetadata().get("name"));
            assertEquals("Hello, I'm John Doe.", updatedThread.getMetadata().get("content"));

            // Delete the created thread
            ThreadDeletionStatus threadDeletionStatus = null;
            try {
                threadDeletionStatus = client.deleteThread(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(threadId, threadDeletionStatus.getId());
            assertTrue(threadDeletionStatus.isDeleted());
        });
    }

    @Test
    public void threadCRUDWithResponse() {
        client = getAssistantsClient();
        createRunRunner(threadCreationOptions -> {
            // Create a thread
            Response<BinaryData> response = client.createThreadWithResponse(
                    BinaryData.fromObject(threadCreationOptions), new RequestOptions());
            AssistantThread assistantThread = null;
            try {
                assistantThread = assertAndGetValueFromResponse(response, AssistantThread.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = assistantThread.getId();
            assertNotNull(threadId);
            assertNotNull(assistantThread.getCreatedAt());
            assertEquals("thread", assistantThread.getObject());

            // Get a thread
            Response<BinaryData> retrievedThreadResponse = client.getThreadWithResponse(threadId, new RequestOptions());
            AssistantThread retrievedThread = null;
            try {
                retrievedThread = assertAndGetValueFromResponse(retrievedThreadResponse, AssistantThread.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(threadId, retrievedThread.getId());
            assertNotNull(retrievedThread.getCreatedAt());
            assertEquals("thread", retrievedThread.getObject());

            // Update a thread
            Map<String, String> metadata = new HashMap<>();
            metadata.put("role", "user");
            metadata.put("name", "John Doe");
            metadata.put("content", "Hello, I'm John Doe.");
            Map<String, Object> requestObj = new HashMap<>();
            requestObj.put("metadata", metadata);
            Response<BinaryData> updateThreadWithResponse = client.updateThreadWithResponse(threadId, BinaryData.fromObject(requestObj),
                    new RequestOptions());
            AssistantThread updatedThread = null;
            try {
                updatedThread = assertAndGetValueFromResponse(updateThreadWithResponse, AssistantThread.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(threadId, updatedThread.getId());
            assertEquals("user", updatedThread.getMetadata().get("role"));
            assertEquals("John Doe", updatedThread.getMetadata().get("name"));
            assertEquals("Hello, I'm John Doe.", updatedThread.getMetadata().get("content"));

            // Delete the created thread
            Response<BinaryData> deletedThreadWithResponse = client.deleteThreadWithResponse(threadId, new RequestOptions());
            ThreadDeletionStatus deletionStatus = null;
            try {
                deletionStatus =
                    assertAndGetValueFromResponse(deletedThreadWithResponse, ThreadDeletionStatus.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(threadId, deletionStatus.getId());
            assertTrue(deletionStatus.isDeleted());
        });
    }
}
