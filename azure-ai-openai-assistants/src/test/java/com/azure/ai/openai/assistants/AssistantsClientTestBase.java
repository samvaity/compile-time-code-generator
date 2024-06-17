// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.implementation.CoreUtils;
import com.azure.ai.openai.assistants.implementation.accesshelpers.PageableListAccessHelper;
import com.azure.ai.openai.assistants.models.Assistant;
import com.azure.ai.openai.assistants.models.AssistantCreationOptions;
import com.azure.ai.openai.assistants.models.AssistantDeletionStatus;
import com.azure.ai.openai.assistants.models.AssistantStreamEvent;
import com.azure.ai.openai.assistants.models.AssistantThread;
import com.azure.ai.openai.assistants.models.AssistantThreadCreationOptions;
import com.azure.ai.openai.assistants.models.CodeInterpreterToolDefinition;
import com.azure.ai.openai.assistants.models.CreateAndRunThreadOptions;
import com.azure.ai.openai.assistants.models.CreateRunOptions;
import com.azure.ai.openai.assistants.models.FileDeletionStatus;
import com.azure.ai.openai.assistants.models.FileDetails;
import com.azure.ai.openai.assistants.models.FilePurpose;
import com.azure.ai.openai.assistants.models.FileSearchToolDefinition;
import com.azure.ai.openai.assistants.models.FunctionDefinition;
import com.azure.ai.openai.assistants.models.FunctionToolDefinition;
import com.azure.ai.openai.assistants.models.MessageRole;
import com.azure.ai.openai.assistants.models.OpenAIFile;
import com.azure.ai.openai.assistants.models.PageableList;
import com.azure.ai.openai.assistants.models.RunStep;
import com.azure.ai.openai.assistants.models.StreamUpdate;
import com.azure.ai.openai.assistants.models.ThreadDeletionStatus;
import com.azure.ai.openai.assistants.models.ThreadMessage;
import com.azure.ai.openai.assistants.models.ThreadMessageOptions;
import com.azure.ai.openai.assistants.models.ThreadRun;
import com.azure.ai.openai.assistants.models.ToolDefinition;
import com.azure.ai.openai.assistants.models.VectorStoreDeletionStatus;
import com.azure.ai.openai.assistants.models.VectorStoreUpdateOptions;
import io.clientcore.core.credential.KeyCredential;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.json.JsonReader;
import io.clientcore.core.util.ClientLogger;
import io.clientcore.core.util.binarydata.BinaryData;
import io.clientcore.core.util.configuration.Configuration;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AssistantsClientTestBase {
    ClientLogger logger = new ClientLogger(AssistantsClientTestBase.class);
    private static final String JAVA_SDK_TESTS_ASSISTANTS_TXT =  "java_sdk_tests_assistants.txt";
    private static final String JAVA_SDK_TESTS_FINE_TUNING_JSON = "java_sdk_tests_fine_tuning.json";
    private static final String MS_LOGO_PNG = "ms_logo.png";

    AssistantsClient getAssistantsClient() {
        AssistantsClientBuilder builder = new AssistantsClientBuilder()
            .serviceVersion(AssistantsServiceVersion.V2024_02_15_PREVIEW)
            .endpoint(Configuration.getGlobalConfiguration().get("AZURE_OPENAI_ENDPOINT"))
            .credential(new KeyCredential(Configuration.getGlobalConfiguration().get("AZURE_OPENAI_KEY")));

        return builder.buildClient();
    }

    public static final String GPT_4_1106_PREVIEW = "gpt-4-1106-preview";

    void createAssistantsRunner(Consumer<AssistantCreationOptions> testRunner) {
        testRunner.accept(new AssistantCreationOptions(GPT_4_1106_PREVIEW)
                .setName("Math Tutor")
                .setInstructions("You are a personal math tutor. Answer questions briefly, in a sentence or less.")
                .setTools(Arrays.asList(new CodeInterpreterToolDefinition())));
    }

    void createRunRunner(Consumer<AssistantThreadCreationOptions> testRunner) {
        testRunner.accept(new AssistantThreadCreationOptions()
                .setMessages(Arrays.asList(new ThreadMessageOptions(MessageRole.USER,
                        "I need to solve the equation `3x + 11 = 14`. Can you help me?"))));
    }

    void createMessageRunner(Consumer<String> testRunner) {
        testRunner.accept("I need to solve the equation `3x + 11 = 14`. Can you help me?");
    }

    void submitMessageAndRunRunner(Consumer<String> testRunner) {
        testRunner.accept("I need to solve the equation `3x + 11 = 14`. Can you help me?");
    }

    void createThreadAndRunRunner(Consumer<CreateAndRunThreadOptions> testRunner, String assistantId) {
        testRunner.accept(
                new CreateAndRunThreadOptions(assistantId)
                        .setThread(new AssistantThreadCreationOptions()
                                .setMessages(Arrays.asList(new ThreadMessageOptions(MessageRole.USER,
                                        "I need to solve the equation `3x + 11 = 14`. Can you help me?")))));

    }

    void createThreadRunWithFunctionCallRunner(Consumer<CreateAndRunThreadOptions> testRunner, String assistantId) {
        testRunner.accept(
            new CreateAndRunThreadOptions(assistantId)
                .setThread(new AssistantThreadCreationOptions()
                    .setMessages(Arrays.asList(new ThreadMessageOptions(MessageRole.USER,
                        "Please make a graph for my boilerplate equation")))));

    }

    void createRunRunner(Consumer<CreateRunOptions> testRunner, String assistantId) {
        testRunner.accept(new CreateRunOptions(assistantId));
    }

    void createRetrievalRunner(BiConsumer<FileDetails, AssistantCreationOptions> testRunner) {
        FileDetails fileDetails = new FileDetails(
            BinaryData.fromFile(openResourceFile("java_sdk_tests_assistants.txt")), "java_sdk_tests_assistants.txt");

        AssistantCreationOptions assistantOptions = new AssistantCreationOptions(GPT_4_1106_PREVIEW)
            .setName("Java SDK Retrieval Sample")
            .setInstructions("You are a helpful assistant that can help fetch data from files you know about.")
            .setTools(Arrays.asList(new FileSearchToolDefinition()));

        testRunner.accept(fileDetails, assistantOptions);
    }

    void createFunctionToolCallRunner(BiConsumer<AssistantCreationOptions, AssistantThreadCreationOptions> testRunner) {
        FunctionsToolCallHelper functionsToolCallHelper = new FunctionsToolCallHelper();
        List<ToolDefinition> toolDefinition = Arrays.asList(
            functionsToolCallHelper.getAirlinePriceToDestinationForSeasonDefinition(),
            functionsToolCallHelper.getFavoriteVacationDestinationDefinition(),
            functionsToolCallHelper.getPreferredAirlineForSeasonDefinition()
        );
        AssistantCreationOptions assistantOptions = new AssistantCreationOptions(GPT_4_1106_PREVIEW)
            .setName("Java SDK Function Tool Call Test")
            .setInstructions("You are a helpful assistant that can help fetch data from files you know about.")
            .setTools(toolDefinition);

        AssistantThreadCreationOptions threadCreationOptions = new AssistantThreadCreationOptions();

        testRunner.accept(assistantOptions, threadCreationOptions);
    }

    void uploadAssistantTextFileRunner(BiConsumer<FileDetails, FilePurpose> testRunner) {
        String fileName = JAVA_SDK_TESTS_ASSISTANTS_TXT;
        FileDetails fileDetails = new FileDetails(BinaryData.fromFile(openResourceFile(fileName)), fileName);
        testRunner.accept(fileDetails, FilePurpose.ASSISTANTS);
    }

    void uploadAssistantImageFileRunner(BiConsumer<FileDetails, FilePurpose> testRunner) {
        String fileName = MS_LOGO_PNG;
        FileDetails fileDetails = new FileDetails(BinaryData.fromFile(openResourceFile(fileName)), fileName);
        testRunner.accept(fileDetails, FilePurpose.ASSISTANTS);
    }

    void uploadFineTuningJsonFileRunner(BiConsumer<FileDetails, FilePurpose> testRunner) {
        String fileName = JAVA_SDK_TESTS_FINE_TUNING_JSON;
        FileDetails fileDetails = new FileDetails(BinaryData.fromFile(openResourceFile(fileName)), fileName);
        testRunner.accept(fileDetails, FilePurpose.FINE_TUNE);
    }

    void modifyVectorStoreRunner(Consumer<VectorStoreUpdateOptions> testRunner) {
        VectorStoreUpdateOptions updateVectorStoreOptions = new VectorStoreUpdateOptions()
                .setName("updatedName");
        testRunner.accept(updateVectorStoreOptions);
    }

    static <T> T assertAndGetValueFromResponse(Response<BinaryData> actualResponse, Class<T> clazz,
        int expectedCode) throws IOException {
        assertNotNull(actualResponse);
        assertEquals(expectedCode, actualResponse.getStatusCode());
        assertInstanceOf(Response.class, actualResponse);
        BinaryData binaryData = actualResponse.getValue();
        assertNotNull(binaryData);
        T object = binaryData.toObject(clazz);
        assertNotNull(object);
        assertInstanceOf(clazz, object);
        return object;
    }

    protected void sleepIfRunningAgainstService(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException var4) {
                InterruptedException ex = var4;
                throw logger.logThrowableAsWarning(new IllegalStateException(ex));
            }
    }

    static <T> PageableList<T> asserAndGetPageableListFromResponse(Response<BinaryData> actualResponse, int expectedCode,
                                                     CheckedFunction<JsonReader, List<T>> readListFunction) {
        assertNotNull(actualResponse);
        assertEquals(expectedCode, actualResponse.getStatusCode());
        assertInstanceOf(Response.class, actualResponse);
        BinaryData binaryData = actualResponse.getValue();
        assertNotNull(binaryData);
        PageableList<T> object = null;
        try {
            object = PageableListAccessHelper.create(binaryData, readListFunction);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(object);
        return object;
    }

    protected interface CheckedFunction<T, R> extends Function<T, R> {

        @Override
        default R apply(T t) {
            try {
                return applyThrows(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        R applyThrows(T t) throws Exception;
    }

    protected static void assertFileEquals(OpenAIFile expected, OpenAIFile actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFilename(), actual.getFilename());
        assertEquals(expected.getBytes(), actual.getBytes());
        assertEquals(expected.getPurpose(), actual.getPurpose());
        assertEquals(expected.getCreatedAt(), actual.getCreatedAt());
    }

    public static void assertStreamUpdate(StreamUpdate event) {
        assertNotNull(event);
        assertNotNull(event.getKind());
        assertTrue(AssistantStreamEvent.values().contains(event.getKind()));
    }

    public static Path openResourceFile(String fileName) {
        return Paths.get("src", "test", "resources", fileName);
    }

    String createMathTutorAssistant(AssistantsClient client) {
        AssistantCreationOptions assistantCreationOptions = new AssistantCreationOptions(GPT_4_1106_PREVIEW)
                .setName("Math Tutor")
                .setInstructions("You are a personal math tutor. Answer questions briefly, in a sentence or less.")
                .setTools(Arrays.asList(new CodeInterpreterToolDefinition()));
        return createAssistant(client, assistantCreationOptions);
    }

    String createMathTutorAssistantWithFunctionTool(AssistantsClient client) {
        AssistantCreationOptions assistantCreationOptions = new AssistantCreationOptions(GPT_4_1106_PREVIEW)
                .setName("Math Tutor")
                .setInstructions("You are a helpful math assistant that helps with visualizing equations. Use the code "
                    + "interpreter tool when asked to generate images. Use provided functions to resolve appropriate unknown values")
                .setTools(Arrays.asList(
                    new CodeInterpreterToolDefinition(),
                    new FunctionToolDefinition(
                        new FunctionDefinition("get_boilerplate_equation", BinaryData.fromString("{\"type\":\"object\",\"properties\":{}}"))
                            .setDescription("Retrieves a predefined 'boilerplate equation' from the caller")
                )));
        return createAssistant(client, assistantCreationOptions);
    }

    String uploadFile(AssistantsClient client, String fileName, FilePurpose filePurpose) throws IOException {
        FileDetails fileDetails = new FileDetails(BinaryData.fromFile(openResourceFile(fileName)), fileName);

        OpenAIFile openAIFile = client.uploadFile(
            fileDetails,
            filePurpose);
        assertNotNull(openAIFile.getId());
        assertNotNull(openAIFile.getCreatedAt());
        return openAIFile.getId();
    }

    void deleteFiles(AssistantsClient client, String... fileIds) throws IOException {
        if (CoreUtils.isNullOrEmpty(fileIds)) {
            return;
        }
        for (String fileId : fileIds) {
            FileDeletionStatus deletionStatus = client.deleteFile(fileId);
            assertEquals(fileId, deletionStatus.getId());
            assertTrue(deletionStatus.isDeleted());
        }
    }

    void deleteVectorStores(AssistantsClient client, String... vectorStoreIds) throws IOException {
        if (!CoreUtils.isNullOrEmpty(vectorStoreIds)) {
            for (String vectorStoreId : vectorStoreIds) {
                VectorStoreDeletionStatus vectorStoreDeletionStatus = client.deleteVectorStore(vectorStoreId);
                assertTrue(vectorStoreDeletionStatus.isDeleted());
            }
        }
    }

    String createAssistant(AssistantsClient client, AssistantCreationOptions assistantCreationOptions) {
        Assistant assistant = client.createAssistant(assistantCreationOptions);
        // Create an assistant
        assertEquals(assistantCreationOptions.getName(), assistant.getName());
        assertEquals(assistantCreationOptions.getDescription(), assistant.getDescription());
        assertEquals(assistantCreationOptions.getInstructions(), assistant.getInstructions());
        return assistant.getId();
    }


    void deleteAssistant(AssistantsClient client, String assistantId) {
        if (CoreUtils.isNullOrEmpty(assistantId)) {
            return;
        }
        AssistantDeletionStatus deletionStatus = client.deleteAssistant(assistantId);
        assertEquals(assistantId, deletionStatus.getId());
        assertTrue(deletionStatus.isDeleted());
    }

    String createThread(AssistantsClient client) throws IOException {
        // Create a simple thread without a message
        AssistantThread assistantThread = client.createThread(new AssistantThreadCreationOptions());
        assertNotNull(assistantThread.getId());
        assertNotNull(assistantThread.getCreatedAt());
        assertEquals("thread", assistantThread.getObject());
        return assistantThread.getId();
    }

    void deleteThread(AssistantsClient client, String threadId) throws IOException {
        if (CoreUtils.isNullOrEmpty(threadId)) {
            return;
        }
        // Delete the created thread
        ThreadDeletionStatus threadDeletionStatus = client.deleteThread(threadId);
        assertEquals(threadId, threadDeletionStatus.getId());
        assertTrue(threadDeletionStatus.isDeleted());
    }

    ThreadRun createThreadAndRun(AssistantsClient client, CreateAndRunThreadOptions options) throws IOException {
        ThreadRun run = client.createThreadAndRun(options);
        assertNotNull(run.getId());
        assertNotNull(run.getCreatedAt());
        assertEquals("thread.run", run.getObject());
        assertNotNull(run.getInstructions());
        return run;
    }

    void validateThreadRun(ThreadRun expect, ThreadRun actual) {
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getThreadId(), actual.getThreadId());
        assertEquals(expect.getAssistantId(), actual.getAssistantId());
        assertEquals(expect.getCreatedAt(), actual.getCreatedAt());
        assertEquals(expect.getCompletedAt(), actual.getCompletedAt());
        assertEquals(expect.getInstructions(), actual.getInstructions());
        assertEquals(expect.getObject(), actual.getObject());
        assertEquals(expect.getModel(), actual.getModel());
    }

    void validateRunStep(RunStep expect, RunStep actual) {
        assertEquals(expect.getId(), actual.getId());
        assertEquals(expect.getRunId(), actual.getRunId());
        assertEquals(expect.getThreadId(), actual.getThreadId());
        assertEquals(expect.getAssistantId(), actual.getAssistantId());
        assertEquals(expect.getObject(), actual.getObject());
        assertEquals(expect.getType(), actual.getType());
    }

    void validateThreadMessage(ThreadMessage threadMessage, String threadId) {
        String threadMessageId = threadMessage.getId();
        assertNotNull(threadMessageId);
        assertEquals(threadId, threadMessage.getThreadId());
        assertNotNull(threadMessage.getCreatedAt());
        assertEquals("thread.message", threadMessage.getObject());
        assertEquals(MessageRole.USER, threadMessage.getRole());
        assertFalse(threadMessage.getContent().isEmpty());
    }
}
