// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.models;


import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;

/**
 * Represents the data of a streamed file citation as applied to a streaming text content part.
 */

public final class MessageDeltaTextFileCitationAnnotation
    implements JsonSerializable<MessageDeltaTextFileCitationAnnotation> {

    /*
     * The ID of the specific file the citation is from.
     */
    
    private String fileId;

    /*
     * The specific quote in the cited file.
     */
    
    private String quote;

    /**
     * Creates an instance of MessageDeltaTextFileCitationAnnotation class.
     */
    
    private MessageDeltaTextFileCitationAnnotation() {
    }

    /**
     * Get the fileId property: The ID of the specific file the citation is from.
     *
     * @return the fileId value.
     */
    
    public String getFileId() {
        return this.fileId;
    }

    /**
     * Get the quote property: The specific quote in the cited file.
     *
     * @return the quote value.
     */
    
    public String getQuote() {
        return this.quote;
    }

    /**
     * {@inheritDoc}
     */
    
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("file_id", this.fileId);
        jsonWriter.writeStringField("quote", this.quote);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MessageDeltaTextFileCitationAnnotation from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of MessageDeltaTextFileCitationAnnotation if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the MessageDeltaTextFileCitationAnnotation.
     */
    
    public static MessageDeltaTextFileCitationAnnotation fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MessageDeltaTextFileCitationAnnotation deserializedMessageDeltaTextFileCitationAnnotation
                = new MessageDeltaTextFileCitationAnnotation();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("file_id".equals(fieldName)) {
                    deserializedMessageDeltaTextFileCitationAnnotation.fileId = reader.getString();
                } else if ("quote".equals(fieldName)) {
                    deserializedMessageDeltaTextFileCitationAnnotation.quote = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedMessageDeltaTextFileCitationAnnotation;
        });
    }
}
