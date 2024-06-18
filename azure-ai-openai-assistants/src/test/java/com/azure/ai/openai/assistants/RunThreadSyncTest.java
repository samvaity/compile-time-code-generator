// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.CreateRunOptions;
import com.azure.ai.openai.assistants.models.MessageRole;
import com.azure.ai.openai.assistants.models.PageableList;
import com.azure.ai.openai.assistants.models.RunStatus;
import com.azure.ai.openai.assistants.models.RunStep;
import com.azure.ai.openai.assistants.models.ThreadMessage;
import com.azure.ai.openai.assistants.models.ThreadMessageOptions;
import com.azure.ai.openai.assistants.models.ThreadRun;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.binarydata.BinaryData;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RunThreadSyncTest extends AssistantsClientTestBase {
    private AssistantsClient client;

    @Test
    public void submitMessageAndRun() throws IOException {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        String threadId = createThread(client);
        submitMessageAndRunRunner(message -> {
            ThreadMessage threadMessage = null;
            try {
                threadMessage = client.createMessage(threadId, new ThreadMessageOptions(MessageRole.USER, message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(threadMessage, threadId);
            // Submit the message and run
            ThreadRun run = null;
            try {
                run = client.createRun(threadId, new CreateRunOptions(mathTutorAssistantId));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(run.getId());
            assertNotNull(run.getCreatedAt());
            assertEquals("thread.run", run.getObject());
            assertEquals(mathTutorAssistantId, run.getAssistantId());
            assertEquals(threadId, run.getThreadId());
            assertNotNull(run.getInstructions());

            // Wait on Run and poll the Run in a loop
            do {
                try {
                    run = client.getRun(run.getThreadId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sleepIfRunningAgainstService(1000);
            } while (run.getStatus() == RunStatus.IN_PROGRESS || run.getStatus() == RunStatus.QUEUED);

            assertSame(RunStatus.COMPLETED, run.getStatus());

            // List the messages, it should contain the answer other than the question.
            PageableList<ThreadMessage> openAIPageableListOfThreadMessage = null;
            try {
                openAIPageableListOfThreadMessage = client.listMessages(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(openAIPageableListOfThreadMessage);
            assertTrue(openAIPageableListOfThreadMessage.getData().size() > 1);
        });
        // Delete the created thread
        deleteThread(client, threadId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void submitMessageAndRunWithResponse() throws IOException {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        String threadId = createThread(client);
        submitMessageAndRunRunner(message -> {
            ThreadMessage threadMessage = null;
            try {
                threadMessage = client.createMessage(threadId, new ThreadMessageOptions(MessageRole.USER, message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            validateThreadMessage(threadMessage, threadId);
            // Submit the message and run
            Response<BinaryData> runWithResponse = client.createRunWithResponse(threadId,
                    BinaryData.fromObject(new CreateRunOptions(mathTutorAssistantId)),
                    new RequestOptions());
            ThreadRun run = null;
            try {
                run = assertAndGetValueFromResponse(runWithResponse, ThreadRun.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(run.getId());
            assertNotNull(run.getCreatedAt());
            assertEquals("thread.run", run.getObject());
            assertEquals(mathTutorAssistantId, run.getAssistantId());
            assertEquals(threadId, run.getThreadId());
            assertNotNull(run.getInstructions());

            // Wait on Run and poll the Run in a loop
            do {
                try {
                    run = client.getRun(run.getThreadId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sleepIfRunningAgainstService(4000);
            } while (run.getStatus() == RunStatus.IN_PROGRESS || run.getStatus() == RunStatus.QUEUED);

            assertSame(RunStatus.COMPLETED, run.getStatus());

            // List the messages, it should contain the answer other than the question.
            PageableList<ThreadMessage> openAIPageableListOfThreadMessage = null;
            try {
                openAIPageableListOfThreadMessage = client.listMessages(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(openAIPageableListOfThreadMessage);
            assertTrue(openAIPageableListOfThreadMessage.getData().size() > 1);
        });
        // Delete the created thread
        deleteThread(client, threadId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void createThreadAndRun() {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        createThreadAndRunRunner(createAndRunThreadOptions -> {
            // Create a simple thread without a message
            ThreadRun run = null;
            try {
                run = client.createThreadAndRun(createAndRunThreadOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = run.getThreadId();
            assertNotNull(run.getId());
            assertNotNull(run.getCreatedAt());
            assertEquals("thread.run", run.getObject());
            assertEquals(mathTutorAssistantId, run.getAssistantId());
            assertNotNull(run.getInstructions());
            assertNotNull(threadId);

            // Wait on Run and poll the Run in a loop
            do {
                try {
                    run = client.getRun(run.getThreadId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sleepIfRunningAgainstService(1000);
            } while (run.getStatus() == RunStatus.IN_PROGRESS || run.getStatus() == RunStatus.QUEUED);

            assertSame(RunStatus.COMPLETED, run.getStatus());

            // List the messages, it should contain the answer other than the question.
            PageableList<ThreadMessage> openAIPageableListOfThreadMessage = null;
            try {
                openAIPageableListOfThreadMessage = client.listMessages(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(openAIPageableListOfThreadMessage);
            assertTrue(openAIPageableListOfThreadMessage.getData().size() > 1);

            // Delete the created thread
            try {
                deleteThread(client, threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, mathTutorAssistantId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void createThreadAndRunWithResponse() {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        createThreadAndRunRunner(createAndRunThreadOptions -> {
            // Create a simple thread without a message
            Response<BinaryData> response = client.createThreadAndRunWithResponse(
                    BinaryData.fromObject(createAndRunThreadOptions), new RequestOptions());
            ThreadRun run = null;
            try {
                run = assertAndGetValueFromResponse(response, ThreadRun.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = run.getThreadId();
            assertNotNull(run.getId());
            assertNotNull(run.getCreatedAt());
            assertEquals("thread.run", run.getObject());
            assertEquals(mathTutorAssistantId, run.getAssistantId());
            assertNotNull(run.getInstructions());
            assertNotNull(threadId);

            // Wait on Run and poll the Run in a loop
            do {
                try {
                    run = client.getRun(run.getThreadId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sleepIfRunningAgainstService(1000);
            } while (run.getStatus() == RunStatus.IN_PROGRESS || run.getStatus() == RunStatus.QUEUED);

            assertSame(RunStatus.COMPLETED, run.getStatus());

            // List the messages, it should contain the answer other than the question.
            PageableList<ThreadMessage> openAIPageableListOfThreadMessage = null;
            try {
                openAIPageableListOfThreadMessage = client.listMessages(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(openAIPageableListOfThreadMessage);
            assertTrue(openAIPageableListOfThreadMessage.getData().size() > 1);

            // Delete the created thread
            try {
                deleteThread(client, threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, mathTutorAssistantId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void cancelRun() {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        createThreadAndRunRunner(createAndRunThreadOptions -> {
            // Create a simple thread without a message
            ThreadRun run = null;
            try {
                run = createThreadAndRun(client, createAndRunThreadOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = run.getThreadId();
            ThreadRun cancelRun = null;
            try {
                cancelRun = client.cancelRun(threadId, run.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RunStatus status = cancelRun.getStatus();
            assertTrue(status == RunStatus.CANCELLING || status == RunStatus.CANCELLED);
            assertEquals(threadId, cancelRun.getThreadId());
            assertEquals(run.getId(), cancelRun.getId());
            // Delete the created thread
            try {
                deleteThread(client, threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, mathTutorAssistantId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void cancelRunWithResponse() {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        createThreadAndRunRunner(createAndRunThreadOptions -> {
            ThreadRun run = null;
            try {
                run = createThreadAndRun(client, createAndRunThreadOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = run.getThreadId();
            String runId = run.getId();
            Response<BinaryData> response = client.cancelRunWithResponse(threadId, runId, new RequestOptions());
            ThreadRun cancelRun = null;
            try {
                cancelRun = assertAndGetValueFromResponse(response, ThreadRun.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            RunStatus status = cancelRun.getStatus();
            assertTrue(status == RunStatus.CANCELLING || status == RunStatus.CANCELLED);
            assertEquals(threadId, cancelRun.getThreadId());
            assertEquals(runId, cancelRun.getId());
            // Delete the created thread
            try {
                deleteThread(client, threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, mathTutorAssistantId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void listRuns() {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        createThreadAndRunRunner(createAndRunThreadOptions -> {
            ThreadRun run = null;
            try {
                run = createThreadAndRun(client, createAndRunThreadOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = run.getThreadId();
            // List runs
            PageableList<ThreadRun> runs = null;
            try {
                runs = client.listRuns(threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<ThreadRun> data = runs.getData();
            assertNotNull(data);
            assertEquals(1, data.size());
            validateThreadRun(run, data.get(0));
            // List runs with response
            Response<BinaryData> response = client.listRunsWithResponse(threadId, new RequestOptions());
            PageableList<ThreadRun> runsWithResponse = asserAndGetPageableListFromResponse(response, 200,
                reader -> reader.readArray(ThreadRun::fromJson));
            List<ThreadRun> dataWithResponse = runsWithResponse.getData();
            assertNotNull(dataWithResponse);
            assertEquals(1, dataWithResponse.size());
            validateThreadRun(run, dataWithResponse.get(0));
            // Delete the created thread
            try {
                deleteThread(client, threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, mathTutorAssistantId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }

    @Test
    public void listAndGetRunSteps() {
        client = getAssistantsClient();
        String mathTutorAssistantId = createMathTutorAssistant(client);
        createThreadAndRunRunner(createAndRunThreadOptions -> {
            ThreadRun run = null;
            try {
                run = createThreadAndRun(client, createAndRunThreadOptions);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String threadId = run.getThreadId();
            String runId = run.getId();

            // Wait on Run and poll the Run in a loop
            do {
                try {
                    run = client.getRun(run.getThreadId(), run.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sleepIfRunningAgainstService(1000);
            } while (run.getStatus() == RunStatus.IN_PROGRESS || run.getStatus() == RunStatus.QUEUED);

            assertSame(RunStatus.COMPLETED, run.getStatus());

            // List run steps
            PageableList<RunStep> runSteps = null;
            try {
                runSteps = client.listRunSteps(threadId, runId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(runSteps);

            List<RunStep> runStepsData = runSteps.getData();
            assertNotNull(runStepsData);
            assertFalse(runStepsData.isEmpty());
            assertEquals("list", runSteps.getObject());
            RunStep runStep = runStepsData.get(0);
            // Get run step by id
            String runStepId = runStep.getId();
            RunStep retrievedStep = null;
            try {
                retrievedStep = client.getRunStep(threadId, runId, runStepId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(retrievedStep);
            validateRunStep(runStep, retrievedStep);

            // WITH RESPONSE

            // List run steps with response
            Response<BinaryData> response = client.listRunStepsWithResponse(threadId, runId, new RequestOptions());
            PageableList<RunStep> runStepsWithResponse = asserAndGetPageableListFromResponse(response, 200,
                reader -> reader.readArray(RunStep::fromJson));
            assertNotNull(runStepsWithResponse);
            List<RunStep> runStepsDataWithResponse = runStepsWithResponse.getData();
            assertNotNull(runStepsDataWithResponse);
            assertFalse(runStepsDataWithResponse.isEmpty());
            assertEquals("list", runSteps.getObject());
            // Get run step with response
            Response<BinaryData> getRunStepResponse = client.getRunStepWithResponse(threadId, run.getId(),
                    runStepId, new RequestOptions());
            RunStep retrievedStepResponse = null;
            try {
                retrievedStepResponse = assertAndGetValueFromResponse(getRunStepResponse, RunStep.class, 200);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(retrievedStepResponse);
            validateRunStep(runStep, retrievedStep);

            // Delete the created thread
            try {
                deleteThread(client, threadId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, mathTutorAssistantId);
        // Delete the created assistant
        deleteAssistant(client, mathTutorAssistantId);
    }
}
