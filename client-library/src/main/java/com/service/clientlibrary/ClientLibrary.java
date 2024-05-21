package com.service.clientlibrary;

import com.service.clientlibrary.implementation.TodoItemsAttachmentsService;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.util.binarydata.BinaryData;

// This is the (commonly hand-written, but not always) convenience layer that the end user will interact with.
public class ClientLibrary {

    // NOTE: Normally these properties would be set by the user in a builder, but for now
    // they are simply hard coded here.
    // FIXME empty pipeline is not so useful here...
    private final TodoItemsAttachmentsService todoItemsAttachmentsService = TodoItemsAttachmentsService.getInstance(null);

    private final String endpoint = "https://localhost:8080";

    public Response<BinaryData> list(long itemId, String accept) {
        return todoItemsAttachmentsService.listSync(endpoint, itemId, accept, null);
    }

    public Response<Void> createAttachment(long itemId, String accept, BinaryData contents) {
        return todoItemsAttachmentsService.createAttachmentSync(endpoint, itemId, accept, contents, null);
    }
}