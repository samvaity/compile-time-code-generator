// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.Assistant;
import com.azure.ai.openai.assistants.models.AssistantThread;
import com.azure.ai.openai.assistants.models.MessageRole;
import com.azure.ai.openai.assistants.models.PageableList;
import com.azure.ai.openai.assistants.models.RequiredFunctionToolCall;
import com.azure.ai.openai.assistants.models.RequiredToolCall;
import com.azure.ai.openai.assistants.models.RunStatus;
import com.azure.ai.openai.assistants.models.RunStep;
import com.azure.ai.openai.assistants.models.RunStepToolCallDetails;
import com.azure.ai.openai.assistants.models.SubmitToolOutputsAction;
import com.azure.ai.openai.assistants.models.ThreadMessageOptions;
import com.azure.ai.openai.assistants.models.ThreadRun;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AzureFunctionsSyncTests extends AssistantsClientTestBase {

    private AssistantsClient client;

    @Test
    public void parallelFunctionCallTest() {
        client = getAssistantsClient();

        createFunctionToolCallRunner((assistantCreationOptions, assistantThreadCreationOptions) -> {
            // Create the assistant
            Assistant assistant = client.createAssistant(assistantCreationOptions);
            // Create the assistant thread
            AssistantThread assistantThread = null;
            try {
                assistantThread = client.createThread(assistantThreadCreationOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Send first user message
            try {
                client.createMessage(
                    assistantThread.getId(),
                    new ThreadMessageOptions(
                        MessageRole.USER,
                        "Assuming both my usually preferred vacation spot and favourite airline carrier, how much would it cost "
                            + "to fly there in September?"
                ));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Create run thread
            ThreadRun run = null;
            try {
                run = client.createRun(assistantThread, assistant);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Poll the run
            do {
                sleepIfRunningAgainstService(1000);
                try {
                    run = client.getRun(assistantThread.getId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (run.getStatus() == RunStatus.QUEUED || run.getStatus() == RunStatus.IN_PROGRESS);

            assertEquals(RunStatus.REQUIRES_ACTION, run.getStatus());
            SubmitToolOutputsAction outputsAction = (SubmitToolOutputsAction) run.getRequiredAction();
            assertNotNull(outputsAction.getSubmitToolOutputs());
            assertFalse(outputsAction.getSubmitToolOutputs().getToolCalls().isEmpty());

            for (RequiredToolCall outputAction : outputsAction.getSubmitToolOutputs().getToolCalls()) {
                assertInstanceOf(RequiredFunctionToolCall.class, outputAction);
            }

            PageableList<RunStep> runSteps = null;
            try {
                runSteps = client.listRunSteps(assistantThread.getId(), run.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFalse(runSteps.getData().isEmpty());

            RunStepToolCallDetails toolCallDetails = (RunStepToolCallDetails) runSteps.getData().get(0).getStepDetails();
            assertFalse(toolCallDetails.getToolCalls().isEmpty());

            // cleanup
            try {
                client.deleteThread(assistantThread.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            client.deleteAssistant(assistant.getId());
        });
    }

}
