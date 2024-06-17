// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.implementation.models.FileListResponse;
import com.azure.ai.openai.assistants.models.FileDeletionStatus;
import com.azure.ai.openai.assistants.models.FilePurpose;
import com.azure.ai.openai.assistants.models.OpenAIFile;
import io.clientcore.core.http.models.RequestOptions;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.binarydata.BinaryData;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesSyncTests extends AssistantsClientTestBase {

    private AssistantsClient client;

    @Test
    public void assistantTextFileOperations() {
        client = getAssistantsClient();
        uploadAssistantTextFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(file);
            assertNotNull(file.getId());

            // Get single file
            OpenAIFile fileFromBackend = null;
            try {
                fileFromBackend = client.getFile(file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            List<OpenAIFile> files = null;
            try {
                files = client.listFiles(filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            final OpenAIFile finalFile = file;
            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            FileDeletionStatus deletionStatus = null;
            try {
                deletionStatus = client.deleteFile(file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
        });
    }

    @Test
    public void assistantImageFileOperations() {
        client = getAssistantsClient();
        uploadAssistantImageFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(file);
            assertNotNull(file.getId());

            // Get single file
            OpenAIFile fileFromBackend = null;
            try {
                fileFromBackend = client.getFile(file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            List<OpenAIFile> files = null;
            try {
                files = client.listFiles(filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            final OpenAIFile finalFile = file;
            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            FileDeletionStatus deletionStatus = null;
            try {
                deletionStatus = client.deleteFile(file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
        });
    }

    @Test
    public void fineTuningJsonFileOperations() {
        client = getAssistantsClient();
        uploadFineTuningJsonFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(file);
            assertNotNull(file.getId());

            // Get single file
            OpenAIFile fileFromBackend = null;
            try {
                fileFromBackend = client.getFile(file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            List<OpenAIFile> files = null;
            try {
                files = client.listFiles(filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            final OpenAIFile finalFile = file;
            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            FileDeletionStatus deletionStatus = null;
            try {
                deletionStatus = client.deleteFile(file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
        });
    }

    @Test
    public void assistantTextFileOperationsWithResponse() {
        client = getAssistantsClient();
        uploadAssistantTextFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(file);
            assertNotNull(file.getId());

            Response<BinaryData> getFileResponse = client.getFileWithResponse(file.getId(), new RequestOptions());
            assertEquals(200, getFileResponse.getStatusCode());
            OpenAIFile fileFromBackend = null;
            try {
                fileFromBackend = getFileResponse.getValue().toObject(OpenAIFile.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.addQueryParam("purpose", FilePurpose.ASSISTANTS.toString());
            Response<BinaryData> listFilesResponse = client.listFilesWithResponse(requestOptions);
            assertEquals(200, listFilesResponse.getStatusCode());
            List<OpenAIFile> files = null;
            try {
                files = listFilesResponse.getValue().toObject(FileListResponse.class).getData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            final OpenAIFile finalFile = file;
            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            Response<BinaryData> deleteResponse = client.deleteFileWithResponse(file.getId(), new RequestOptions());
            assertEquals(200, deleteResponse.getStatusCode());
            FileDeletionStatus deletionStatus = null;
            try {
                deletionStatus = deleteResponse.getValue().toObject(FileDeletionStatus.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
        });
    }

    @Test
    public void assistantImageFileOperationsWithResponse() {
        client = getAssistantsClient();
        uploadAssistantImageFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(file);
            assertNotNull(file.getId());

            // Get single file
            Response<BinaryData> getFileResponse = client.getFileWithResponse(file.getId(), new RequestOptions());
            assertEquals(200, getFileResponse.getStatusCode());
            OpenAIFile fileFromBackend = null;
            try {
                fileFromBackend = getFileResponse.getValue().toObject(OpenAIFile.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.addQueryParam("purpose", FilePurpose.ASSISTANTS.toString());
            Response<BinaryData> listFilesResponse = client.listFilesWithResponse(requestOptions);
            assertEquals(200, listFilesResponse.getStatusCode());
            List<OpenAIFile> files;
            try {
                FileListResponse fileListResponse = listFilesResponse.getValue().toObject(FileListResponse.class);
                files = fileListResponse.getData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            final OpenAIFile finalFile = file;
            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            Response<BinaryData> deleteResponse = client.deleteFileWithResponse(file.getId(), new RequestOptions());
            assertEquals(200, deleteResponse.getStatusCode());
            FileDeletionStatus deletionStatus = null;
            try {
                deletionStatus = deleteResponse.getValue().toObjectFromType(FileDeletionStatus.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
        });
    }

    @Test
    public void fineTuningJsonFileOperationsWithResponse() {
        client = getAssistantsClient();
        uploadFineTuningJsonFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertNotNull(file);
            assertNotNull(file.getId());

            Response<BinaryData> getFileResponse = client.getFileWithResponse(file.getId(), new RequestOptions());
            assertEquals(200, getFileResponse.getStatusCode());
            OpenAIFile fileFromBackend = null;
            try {
                fileFromBackend = getFileResponse.getValue().toObject(OpenAIFile.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.addQueryParam("purpose", FilePurpose.FINE_TUNE.toString());
            Response<BinaryData> listFilesResponse = client.listFilesWithResponse(requestOptions);
            assertEquals(200, listFilesResponse.getStatusCode());
            List<OpenAIFile> files;
            try {
                FileListResponse fileListResponse = listFilesResponse.getValue().toObject(FileListResponse.class);
                files = fileListResponse.getData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            final OpenAIFile finalFile = file;
            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            Response<BinaryData> deleteResponse = client.deleteFileWithResponse(file.getId(), new RequestOptions());
            assertEquals(200, deleteResponse.getStatusCode());
            FileDeletionStatus deletionStatus = null;
            try {
                deletionStatus = deleteResponse.getValue().toObjectFromType(FileDeletionStatus.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
        });
    }
}
