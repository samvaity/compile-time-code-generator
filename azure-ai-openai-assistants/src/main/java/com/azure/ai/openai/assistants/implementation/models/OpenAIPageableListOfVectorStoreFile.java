// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.implementation.models;

import com.azure.ai.openai.assistants.models.VectorStoreFile;

import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The response data for a requested list of items.
 */

public final class OpenAIPageableListOfVectorStoreFile
    implements JsonSerializable<OpenAIPageableListOfVectorStoreFile> {

    /*
     * The object type, which is always list.
     */
    
    private final String object = "list";

    /*
     * The requested list of items.
     */
    
    private final List<VectorStoreFile> data;

    /*
     * The first ID represented in this list.
     */
    
    private final String firstId;

    /*
     * The last ID represented in this list.
     */
    
    private final String lastId;

    /*
     * A value indicating whether there are additional values available not captured in this list.
     */
    
    private final boolean hasMore;

    /**
     * Creates an instance of OpenAIPageableListOfVectorStoreFile class.
     *
     * @param data the data value to set.
     * @param firstId the firstId value to set.
     * @param lastId the lastId value to set.
     * @param hasMore the hasMore value to set.
     */
    
    private OpenAIPageableListOfVectorStoreFile(List<VectorStoreFile> data, String firstId, String lastId,
        boolean hasMore) {
        this.data = data;
        this.firstId = firstId;
        this.lastId = lastId;
        this.hasMore = hasMore;
    }

    /**
     * Get the object property: The object type, which is always list.
     *
     * @return the object value.
     */
    
    public String getObject() {
        return this.object;
    }

    /**
     * Get the data property: The requested list of items.
     *
     * @return the data value.
     */
    
    public List<VectorStoreFile> getData() {
        return this.data;
    }

    /**
     * Get the firstId property: The first ID represented in this list.
     *
     * @return the firstId value.
     */
    
    public String getFirstId() {
        return this.firstId;
    }

    /**
     * Get the lastId property: The last ID represented in this list.
     *
     * @return the lastId value.
     */
    
    public String getLastId() {
        return this.lastId;
    }

    /**
     * Get the hasMore property: A value indicating whether there are additional values available not captured in this
     * list.
     *
     * @return the hasMore value.
     */
    
    public boolean isHasMore() {
        return this.hasMore;
    }

    /**
     * {@inheritDoc}
     */
    
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("object", this.object);
        jsonWriter.writeArrayField("data", this.data, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("first_id", this.firstId);
        jsonWriter.writeStringField("last_id", this.lastId);
        jsonWriter.writeBooleanField("has_more", this.hasMore);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OpenAIPageableListOfVectorStoreFile from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of OpenAIPageableListOfVectorStoreFile if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the OpenAIPageableListOfVectorStoreFile.
     */
    
    public static OpenAIPageableListOfVectorStoreFile fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            List<VectorStoreFile> data = null;
            String firstId = null;
            String lastId = null;
            boolean hasMore = false;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("data".equals(fieldName)) {
                    data = reader.readArray(reader1 -> VectorStoreFile.fromJson(reader1));
                } else if ("first_id".equals(fieldName)) {
                    firstId = reader.getString();
                } else if ("last_id".equals(fieldName)) {
                    lastId = reader.getString();
                } else if ("has_more".equals(fieldName)) {
                    hasMore = reader.getBoolean();
                } else {
                    reader.skipChildren();
                }
            }
            return new OpenAIPageableListOfVectorStoreFile(data, firstId, lastId, hasMore);
        });
    }
}
