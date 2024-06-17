// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.Assistant;
import com.azure.ai.openai.assistants.models.AssistantThread;
import com.azure.ai.openai.assistants.models.AssistantThreadCreationOptions;
import com.azure.ai.openai.assistants.models.CreateFileSearchToolResourceOptions;
import com.azure.ai.openai.assistants.models.CreateFileSearchToolResourceVectorStoreOptions;
import com.azure.ai.openai.assistants.models.CreateFileSearchToolResourceVectorStoreOptionsList;
import com.azure.ai.openai.assistants.models.CreateToolResourcesOptions;
import com.azure.ai.openai.assistants.models.FilePurpose;
import com.azure.ai.openai.assistants.models.MessageRole;
import com.azure.ai.openai.assistants.models.MessageTextContent;
import com.azure.ai.openai.assistants.models.OpenAIFile;
import com.azure.ai.openai.assistants.models.PageableList;
import com.azure.ai.openai.assistants.models.RunStatus;
import com.azure.ai.openai.assistants.models.ThreadMessage;
import com.azure.ai.openai.assistants.models.ThreadMessageOptions;
import com.azure.ai.openai.assistants.models.ThreadRun;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileSearchSyncTest extends AssistantsClientTestBase {

    AssistantsClient client;

    @Test
    public void basicFileSearch() {
        client = getAssistantsClient();

        createRetrievalRunner((fileDetails, assistantCreationOptions) -> {
            // Upload file for assistant
            OpenAIFile openAIFile = null;
            try {
                openAIFile = client.uploadFile(fileDetails, FilePurpose.ASSISTANTS);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Create assistant
            CreateToolResourcesOptions createToolResourcesOptions = new CreateToolResourcesOptions();
            createToolResourcesOptions.setFileSearch(
                new CreateFileSearchToolResourceOptions(
                    new CreateFileSearchToolResourceVectorStoreOptionsList(
                        Arrays.asList(new CreateFileSearchToolResourceVectorStoreOptions(Arrays.asList(openAIFile.getId()))))));
            assistantCreationOptions.setToolResources(createToolResourcesOptions);
            Assistant assistant = client.createAssistant(assistantCreationOptions);

            // Create thread
            AssistantThread thread = null;
            try {
                thread = client.createThread(new AssistantThreadCreationOptions());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Assign message to thread
            try {
                client.createMessage(
                    thread.getId(),
                    new ThreadMessageOptions(
                        MessageRole.USER,
                        "Can you give me the documented codes for 'banana' and 'orange'?"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Pass the message to the assistant and start the run
            ThreadRun run = null;
            try {
                run = client.createRun(thread, assistant);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            do {
                sleepIfRunningAgainstService(1000);
                try {
                    run = client.getRun(thread.getId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (run.getStatus() == RunStatus.IN_PROGRESS
                || run.getStatus() == RunStatus.QUEUED);

            assertEquals(RunStatus.COMPLETED, run.getStatus());
            assertEquals(assistant.getId(), run.getAssistantId());

            // List messages from the thread
            PageableList<ThreadMessage> messageList = null;
            try {
                messageList = client.listMessages(thread.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertEquals(2, messageList.getData().size());
            ThreadMessage firstMessage = messageList.getData().get(0);

            assertEquals(MessageRole.ASSISTANT, firstMessage.getRole());
            assertFalse(firstMessage.getContent().isEmpty());

            MessageTextContent firstMessageContent = (MessageTextContent) firstMessage.getContent().get(0);
            assertNotNull(firstMessageContent);
            assertTrue(firstMessageContent.getText().getValue().contains("232323"));

            // cleanup
            client.deleteAssistant(assistant.getId());
            try {
                client.deleteFile(openAIFile.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                client.deleteThread(thread.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
