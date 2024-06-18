// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.Assistant;
import com.azure.ai.openai.assistants.models.AssistantDeletionStatus;
import com.azure.ai.openai.assistants.models.ListSortOrder;
import com.azure.ai.openai.assistants.models.PageableList;
import com.azure.ai.openai.assistants.models.UpdateAssistantOptions;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.binarydata.BinaryData;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssistantsSyncTest extends AssistantsClientTestBase {
    private AssistantsClient client;

    @Test
    public void assistantCreateRetrieveUpdateDelete() {
        client = getAssistantsClient();
        createAssistantsRunner(assistantCreationOptions -> {
            Assistant assistant = client.createAssistant(assistantCreationOptions);
            // Create an assistant
            assertEquals(assistantCreationOptions.getName(), assistant.getName());
            assertEquals(assistantCreationOptions.getDescription(), assistant.getDescription());
            assertEquals(assistantCreationOptions.getInstructions(), assistant.getInstructions());

            // Retrieve the created assistant
            Assistant retrievedAssistant = null;
            try {
                retrievedAssistant = client.getAssistant(assistant.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(assistant.getId(), retrievedAssistant.getId());
            assertEquals(assistant.getName(), retrievedAssistant.getName());
            assertEquals(assistant.getDescription(), retrievedAssistant.getDescription());
            assertEquals(assistant.getInstructions(), retrievedAssistant.getInstructions());
            assertEquals(assistant.getTools().get(0).getClass(), retrievedAssistant.getTools().get(0).getClass());

            // Update the created assistant
            String updatedName = "updatedName";
            String updatedDescription = "updatedDescription";
            String updatedInstructions = "updatedInstructions";
            Assistant updatedAssistant = null;
            try {
                updatedAssistant = client.updateAssistant(assistant.getId(),
                        new UpdateAssistantOptions()
                                .setName(updatedName)
                                .setDescription(updatedDescription)
                                .setInstructions(updatedInstructions));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(assistant.getId(), updatedAssistant.getId());
            assertEquals(updatedName, updatedAssistant.getName());
            assertEquals(updatedDescription, updatedAssistant.getDescription());
            assertEquals(updatedInstructions, updatedAssistant.getInstructions());
            assertEquals(assistant.getTools().get(0).getClass(), updatedAssistant.getTools().get(0).getClass());

            // Delete the created assistant
            AssistantDeletionStatus assistantDeletionStatus = client.deleteAssistant(assistant.getId());
            assertEquals(assistant.getId(), assistantDeletionStatus.getId());
            assertTrue(assistantDeletionStatus.isDeleted());
        });
    }

    @Test
    public void assistantCrudWithResponse() {
        client = getAssistantsClient();
        createAssistantsRunner(assistantCreationOptions -> {
            // Create an assistant
            Response<BinaryData> response = client.createAssistantWithResponse(BinaryData.fromObject(assistantCreationOptions), new RequestOptions());
            Assistant assistant = null;
            try {
                assistant = assertAndGetValueFromResponse(response, Assistant.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(assistantCreationOptions.getName(), assistant.getName());
            assertEquals(assistantCreationOptions.getDescription(), assistant.getDescription());
            assertEquals(assistantCreationOptions.getInstructions(), assistant.getInstructions());


            // Retrieve the created assistant
            Response<BinaryData> retrievedAssistantResponse = client.getAssistantWithResponse(assistant.getId(),
                    new RequestOptions());
            Assistant retrievedAssistant = null;
            try {
                retrievedAssistant = assertAndGetValueFromResponse(retrievedAssistantResponse, Assistant.class,
                        200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(assistant.getId(), retrievedAssistant.getId());
            assertEquals(assistant.getName(), retrievedAssistant.getName());
            assertEquals(assistant.getDescription(), retrievedAssistant.getDescription());
            assertEquals(assistant.getInstructions(), retrievedAssistant.getInstructions());
            assertEquals(assistant.getTools().get(0).getClass(), retrievedAssistant.getTools().get(0).getClass());

            // Update the created assistant
            String updatedName = "updatedName";
            String updatedDescription = "updatedDescription";
            String updatedInstructions = "updatedInstructions";
            Response<BinaryData> updatedAssistantWithResponse = client.updateAssistantWithResponse(assistant.getId(),
                    BinaryData.fromObject(new UpdateAssistantOptions()
                            .setName(updatedName)
                            .setDescription(updatedDescription)
                            .setInstructions(updatedInstructions)),
                    new RequestOptions());
            Assistant updatedAssistant = null;
            try {
                updatedAssistant = assertAndGetValueFromResponse(updatedAssistantWithResponse, Assistant.class,
                        200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(assistant.getId(), updatedAssistant.getId());
            assertEquals(updatedName, updatedAssistant.getName());
            assertEquals(updatedDescription, updatedAssistant.getDescription());
            assertEquals(updatedInstructions, updatedAssistant.getInstructions());
            assertEquals(assistant.getTools().get(0).getClass(), updatedAssistant.getTools().get(0).getClass());

            // Delete the created assistant
            Response<BinaryData> deletionStatusResponse = client.deleteAssistantWithResponse(assistant.getId(),
                new RequestOptions());
            AssistantDeletionStatus deletionStatus = null;
            try {
                deletionStatus =
                    assertAndGetValueFromResponse(deletionStatusResponse, AssistantDeletionStatus.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertEquals(assistant.getId(), deletionStatus.getId());
            assertTrue(deletionStatus.isDeleted());
        });
    }

    @Test
    public void listAssistants() {
        client = getAssistantsClient();
        createAssistantsRunner(assistantCreationOptions -> {
            // Create assistants
            String assistantId1 = createAssistant(client, assistantCreationOptions.setName("assistant1"));
            String assistantId2 = createAssistant(client, assistantCreationOptions.setName("assistant2"));

            PageableList<Assistant> assistantsAscending = client.listAssistants();
            List<Assistant> dataAscending = assistantsAscending.getData();
            assertTrue(dataAscending.size() >= 2);

            Response<BinaryData> response = client.listAssistantsWithResponse(new RequestOptions());
            PageableList<Assistant> assistantsAscendingResponse = asserAndGetPageableListFromResponse(response, 200,
                reader -> reader.readArray(Assistant::fromJson));
            List<Assistant> dataAscendingResponse = assistantsAscendingResponse.getData();
            assertTrue(dataAscendingResponse.size() >= 2);

            // Deleted created assistant
            deleteAssistant(client, assistantId1);
            deleteAssistant(client, assistantId2);
        });
    }

    @Test
    public void listAssistantsBetweenTwoAssistantId() {
        client = getAssistantsClient();
        createAssistantsRunner(assistantCreationOptions -> {
            // Create assistants
            String assistantId1 = createAssistant(client, assistantCreationOptions.setName("assistant1"));
            String assistantId2 = createAssistant(client, assistantCreationOptions.setName("assistant2"));
            String assistantId3 = createAssistant(client, assistantCreationOptions.setName("assistant3"));
            String assistantId4 = createAssistant(client, assistantCreationOptions.setName("assistant4"));

            // List only the middle two assistants; sort by name ascending
            PageableList<Assistant> assistantsAscending = client.listAssistants(100,
                    ListSortOrder.ASCENDING, assistantId1, assistantId4);
            List<Assistant> dataAscending = assistantsAscending.getData();
            // TODO: confirm, 20 instead of 2
            //  assertEquals(2, dataAscending.size());
            assertEquals(assistantId2, dataAscending.get(0).getId());
            assertEquals(assistantId3, dataAscending.get(1).getId());

            // List only the middle two assistants; sort by name descending
            PageableList<Assistant> assistantsDescending = client.listAssistants(100,
                    ListSortOrder.DESCENDING, assistantId4, assistantId1);
            List<Assistant> dataDescending = assistantsDescending.getData();
            // TODO: confirm, 20 instead of 2
            // assertEquals(20, dataDescending.size());
            assertEquals(assistantId3, dataDescending.get(0).getId());
            assertEquals(assistantId2, dataDescending.get(1).getId());

            // Deleted created assistant
            deleteAssistant(client, assistantId1);
            deleteAssistant(client, assistantId2);
            deleteAssistant(client, assistantId3);
            deleteAssistant(client, assistantId4);
        });
    }
}
