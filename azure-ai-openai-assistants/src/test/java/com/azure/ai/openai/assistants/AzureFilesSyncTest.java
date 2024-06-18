// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import com.azure.ai.openai.assistants.models.FileDeletionStatus;
import com.azure.ai.openai.assistants.models.OpenAIFile;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled("Support for multipart form data is not available in the generated client")
public class AzureFilesSyncTest extends AssistantsClientTestBase {

    private AssistantsClient client;

    @Test
    public void assistantTextFileOperations() {
        client = getAssistantsClient();
        uploadAssistantTextFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);

                assertNotNull(file);
                assertNotNull(file.getId());

                // Get single file
                OpenAIFile fileFromBackend = client.getFile(file.getId());
                assertFileEquals(file, fileFromBackend);

                // Get file by purpose
                List<OpenAIFile> files = client.listFiles(filePurpose);
                final OpenAIFile finalFile = file;
                assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

                // Delete file
                FileDeletionStatus deletionStatus = client.deleteFile(file.getId());
                assertTrue(deletionStatus.isDeleted());
                assertEquals(deletionStatus.getId(), file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

            assertNotNull(file);
            assertNotNull(file.getId());

            // Get single file
            OpenAIFile fileFromBackend = client.getFile(file.getId());
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            List<OpenAIFile> files = client.listFiles(filePurpose);
                final OpenAIFile finalFile = file;
                assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            FileDeletionStatus deletionStatus = client.deleteFile(file.getId());
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Disabled("Support in Azure OpenAI for FINE_TUNE files is not yet available")
    @Test
    public void fineTuningJsonFileOperations() {
        client = getAssistantsClient();
        uploadFineTuningJsonFileRunner((fileDetails, filePurpose) -> {
            // Upload file
            OpenAIFile file = null;
            try {
                file = client.uploadFile(fileDetails, filePurpose);

            assertNotNull(file);
            assertNotNull(file.getId());

            // Get single file
            OpenAIFile fileFromBackend = client.getFile(file.getId());
            assertFileEquals(file, fileFromBackend);

            // Get file by purpose
            List<OpenAIFile> files = client.listFiles(filePurpose);
                final OpenAIFile finalFile = file;
                assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));

            // Delete file
            FileDeletionStatus deletionStatus = client.deleteFile(file.getId());
            assertTrue(deletionStatus.isDeleted());
            assertEquals(deletionStatus.getId(), file.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    //@Test
    //public void assistantTextFileOperationsWithResponse() {
    //    client = getAssistantsClient();
    //    uploadAssistantTextFileRunner((fileDetails, filePurpose) -> {
    //        // Upload file
    //        OpenAIFile file = null;
    //        try {
    //            file = client.uploadFile(fileDetails, filePurpose);
    //
    //        assertNotNull(file);
    //        assertNotNull(file.getId());
    //
    //        // Get single file
    //        Response<BinaryData> getFileResponse = client.getFileWithResponse(file.getId(), new RequestOptions());
    //        assertEquals(200, getFileResponse.getStatusCode());
    //        OpenAIFile fileFromBackend = getFileResponse.getValue().toObject(OpenAIFile.class);
    //        assertFileEquals(file, fileFromBackend);
    //
    //        // Get file by purpose
    //        RequestOptions requestOptions = new RequestOptions();
    //        requestOptions.addQueryParam("purpose", FilePurpose.ASSISTANTS.toString());
    //        Response<BinaryData> listFilesResponse = client.listFilesWithResponse(requestOptions);
    //        assertEquals(200, listFilesResponse.getStatusCode());
    //        List<OpenAIFile> files = listFilesResponse.getValue()
    //            .toObject(FileListResponse.class).getData();
    //            final OpenAIFile finalFile = file;
    //            assertTrue(files.stream().anyMatch(f -> f.getId().equals(finalFile.getId())));
    //
    //        // Delete file
    //        Response<BinaryData> deleteResponse = client.deleteFileWithResponse(file.getId(), new RequestOptions());
    //        assertEquals(200, deleteResponse.getStatusCode());
    //        FileDeletionStatus deletionStatus = deleteResponse.getValue().toObject(FileDeletionStatus.class);
    //        assertTrue(deletionStatus.isDeleted());
    //        assertEquals(deletionStatus.getId(), file.getId());
    //        } catch (IOException e) {
    //            throw new RuntimeException(e);
    //        }
    //    });
    //}

    //@Test
    //public void assistantImageFileOperationsWithResponse() {
    //    client = getAssistantsClient();
    //    uploadAssistantImageFileRunner((fileDetails, filePurpose) -> {
    //        // Upload file
    //        OpenAIFile file = client.uploadFile(fileDetails, filePurpose);
    //        assertNotNull(file);
    //        assertNotNull(file.getId());
    //
    //        // Get single file
    //        Response<BinaryData> getFileResponse = client.getFileWithResponse(file.getId(), new RequestOptions());
    //        assertEquals(200, getFileResponse.getStatusCode());
    //        OpenAIFile fileFromBackend = getFileResponse.getValue().toObject(OpenAIFile.class);
    //        assertFileEquals(file, fileFromBackend);
    //
    //        // Get file by purpose
    //        RequestOptions requestOptions = new RequestOptions();
    //        requestOptions.addQueryParam("purpose", FilePurpose.ASSISTANTS.toString());
    //        Response<BinaryData> listFilesResponse = client.listFilesWithResponse(requestOptions);
    //        assertEquals(200, listFilesResponse.getStatusCode());
    //        List<OpenAIFile> files = listFilesResponse.getValue()
    //            .toObject(FileListResponse.class).getData();
    //        assertTrue(files.stream().anyMatch(f -> f.getId().equals(file.getId())));
    //
    //        // Delete file
    //        Response<BinaryData> deleteResponse = client.deleteFileWithResponse(file.getId(), new RequestOptions());
    //        assertEquals(200, deleteResponse.getStatusCode());
    //        FileDeletionStatus deletionStatus = deleteResponse.getValue().toObject(FileDeletionStatus.class);
    //        assertTrue(deletionStatus.isDeleted());
    //        assertEquals(deletionStatus.getId(), file.getId());
    //    });
    //}

    //@Disabled("Support in Azure OpenAI for FINE_TUNE files is not yet available")
    //@Test
    //public void fineTuningJsonFileOperationsWithResponse() {
    //    client = getAssistantsClient();
    //    uploadFineTuningJsonFileRunner((fileDetails, filePurpose) -> {
    //        // Upload file
    //        OpenAIFile file = null;
    //        try {
    //            file = client.uploadFile(fileDetails, filePurpose);
    //        } catch (IOException e) {
    //            throw new RuntimeException(e);
    //        }
    //        assertNotNull(file);
    //        assertNotNull(file.getId());
    //
    //        // Get single file
    //        Response<BinaryData> getFileResponse = client.getFileWithResponse(file.getId(), new RequestOptions());
    //        assertEquals(200, getFileResponse.getStatusCode());
    //        OpenAIFile fileFromBackend = null;
    //        try {
    //            fileFromBackend = getFileResponse.getValue().toObject(OpenAIFile.class);
    //        } catch (IOException e) {
    //            throw new RuntimeException(e);
    //        }
    //        assertFileEquals(file, fileFromBackend);
    //
    //        // Get file by purpose
    //        RequestOptions requestOptions = new RequestOptions();
    //        requestOptions.addQueryParam("purpose", FilePurpose.FINE_TUNE.toString());
    //        Response<BinaryData> listFilesResponse = client.listFilesWithResponse(requestOptions);
    //        assertEquals(200, listFilesResponse.getStatusCode());
    //        List<OpenAIFile> files = listFilesResponse.getValue()
    //            .toObject(FileListResponse.class).getData();
    //        assertTrue(files.stream().anyMatch(f -> f.getId().equals(file.getId())));
    //
    //        // Delete file
    //        Response<BinaryData> deleteResponse = client.deleteFileWithResponse(file.getId(), new RequestOptions());
    //        assertEquals(200, deleteResponse.getStatusCode());
    //        FileDeletionStatus deletionStatus = deleteResponse.getValue().toObject(FileDeletionStatus.class);
    //        assertTrue(deletionStatus.isDeleted());
    //        assertEquals(deletionStatus.getId(), file.getId());
    //    });
    //}
}
