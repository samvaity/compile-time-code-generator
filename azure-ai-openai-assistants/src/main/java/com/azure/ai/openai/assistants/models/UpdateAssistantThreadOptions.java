// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.models;

import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;
import java.util.Map;

/**
 * The details used to update an existing assistant thread.
 */

public final class UpdateAssistantThreadOptions implements JsonSerializable<UpdateAssistantThreadOptions> {

    /*
     * A set of resources that are made available to the assistant's tools in this thread. The resources are specific to
     * the
     * type of tool. For example, the `code_interpreter` tool requires a list of file IDs, while the `file_search` tool
     * requires
     * a list of vector store IDs
     */
    
    private UpdateToolResourcesOptions toolResources;

    /*
     * A set of up to 16 key/value pairs that can be attached to an object, used for storing additional information
     * about that object in a structured format. Keys may be up to 64 characters in length and values may be up to 512
     * characters in length.
     */
    
    private Map<String, String> metadata;

    /**
     * Creates an instance of UpdateAssistantThreadOptions class.
     */
    
    public UpdateAssistantThreadOptions() {
    }

    /**
     * Get the toolResources property: A set of resources that are made available to the assistant's tools in this
     * thread. The resources are specific to the
     * type of tool. For example, the `code_interpreter` tool requires a list of file IDs, while the `file_search` tool
     * requires
     * a list of vector store IDs.
     *
     * @return the toolResources value.
     */
    
    public UpdateToolResourcesOptions getToolResources() {
        return this.toolResources;
    }

    /**
     * Set the toolResources property: A set of resources that are made available to the assistant's tools in this
     * thread. The resources are specific to the
     * type of tool. For example, the `code_interpreter` tool requires a list of file IDs, while the `file_search` tool
     * requires
     * a list of vector store IDs.
     *
     * @param toolResources the toolResources value to set.
     * @return the UpdateAssistantThreadOptions object itself.
     */
    
    public UpdateAssistantThreadOptions setToolResources(UpdateToolResourcesOptions toolResources) {
        this.toolResources = toolResources;
        return this;
    }

    /**
     * Get the metadata property: A set of up to 16 key/value pairs that can be attached to an object, used for storing
     * additional information about that object in a structured format. Keys may be up to 64 characters in length and
     * values may be up to 512 characters in length.
     *
     * @return the metadata value.
     */
    
    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: A set of up to 16 key/value pairs that can be attached to an object, used for storing
     * additional information about that object in a structured format. Keys may be up to 64 characters in length and
     * values may be up to 512 characters in length.
     *
     * @param metadata the metadata value to set.
     * @return the UpdateAssistantThreadOptions object itself.
     */
    
    public UpdateAssistantThreadOptions setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("tool_resources", this.toolResources);
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UpdateAssistantThreadOptions from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of UpdateAssistantThreadOptions if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the UpdateAssistantThreadOptions.
     */
    
    public static UpdateAssistantThreadOptions fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UpdateAssistantThreadOptions deserializedUpdateAssistantThreadOptions = new UpdateAssistantThreadOptions();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("tool_resources".equals(fieldName)) {
                    deserializedUpdateAssistantThreadOptions.toolResources
                        = UpdateToolResourcesOptions.fromJson(reader);
                } else if ("metadata".equals(fieldName)) {
                    Map<String, String> metadata = reader.readMap(reader1 -> reader1.getString());
                    deserializedUpdateAssistantThreadOptions.metadata = metadata;
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedUpdateAssistantThreadOptions;
        });
    }
}
