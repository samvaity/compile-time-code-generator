// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.models;

import io.clientcore.core.util.binarydata.BinaryData;
import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The request details to use when creating a new assistant.
 */

public final class AssistantCreationOptions implements JsonSerializable<AssistantCreationOptions> {

    /*
     * The ID of the model to use.
     */

    private final String model;

    /*
     * The name of the new assistant.
     */

    private String name;

    /*
     * The description of the new assistant.
     */

    private String description;

    /*
     * The system instructions for the new assistant to use.
     */

    private String instructions;

    /*
     * The collection of tools to enable for the new assistant.
     */

    private List<ToolDefinition> tools;

    /*
     * A set of resources that are used by the assistant's tools. The resources are specific to the type of tool. For
     * example, the `code_interpreter`
     * tool requires a list of file IDs, while the `file_search` tool requires a list of vector store IDs.
     */

    private CreateToolResourcesOptions toolResources;

    /*
     * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     */

    private Double temperature;

    /*
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
     * the tokens with top_p probability mass.
     * So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     * 
     * We generally recommend altering this or temperature but not both.
     */

    private Double topP;

    /*
     * The response format of the tool calls used by this assistant.
     */

    private BinaryData responseFormat;

    /*
     * A set of up to 16 key/value pairs that can be attached to an object, used for storing additional information
     * about that object in a structured format. Keys may be up to 64 characters in length and values may be up to 512
     * characters in length.
     */

    private Map<String, String> metadata;

    /**
     * Creates an instance of AssistantCreationOptions class.
     *
     * @param model the model value to set.
     */

    public AssistantCreationOptions(String model) {
        this.model = model;
    }

    /**
     * Get the model property: The ID of the model to use.
     *
     * @return the model value.
     */

    public String getModel() {
        return this.model;
    }

    /**
     * Get the name property: The name of the new assistant.
     *
     * @return the name value.
     */

    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name of the new assistant.
     *
     * @param name the name value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the description property: The description of the new assistant.
     *
     * @return the description value.
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: The description of the new assistant.
     *
     * @param description the description value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the instructions property: The system instructions for the new assistant to use.
     *
     * @return the instructions value.
     */

    public String getInstructions() {
        return this.instructions;
    }

    /**
     * Set the instructions property: The system instructions for the new assistant to use.
     *
     * @param instructions the instructions value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    /**
     * Get the tools property: The collection of tools to enable for the new assistant.
     *
     * @return the tools value.
     */

    public List<ToolDefinition> getTools() {
        return this.tools;
    }

    /**
     * Set the tools property: The collection of tools to enable for the new assistant.
     *
     * @param tools the tools value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setTools(List<ToolDefinition> tools) {
        this.tools = tools;
        return this;
    }

    /**
     * Get the toolResources property: A set of resources that are used by the assistant's tools. The resources are
     * specific to the type of tool. For example, the `code_interpreter`
     * tool requires a list of file IDs, while the `file_search` tool requires a list of vector store IDs.
     *
     * @return the toolResources value.
     */

    public CreateToolResourcesOptions getToolResources() {
        return this.toolResources;
    }

    /**
     * Set the toolResources property: A set of resources that are used by the assistant's tools. The resources are
     * specific to the type of tool. For example, the `code_interpreter`
     * tool requires a list of file IDs, while the `file_search` tool requires a list of vector store IDs.
     *
     * @param toolResources the toolResources value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setToolResources(CreateToolResourcesOptions toolResources) {
        this.toolResources = toolResources;
        return this;
    }

    /**
     * Get the temperature property: What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make
     * the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     *
     * @return the temperature value.
     */

    public Double getTemperature() {
        return this.temperature;
    }

    /**
     * Set the temperature property: What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make
     * the output more random,
     * while lower values like 0.2 will make it more focused and deterministic.
     *
     * @param temperature the temperature value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * Get the topP property: An alternative to sampling with temperature, called nucleus sampling, where the model
     * considers the results of the tokens with top_p probability mass.
     * So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     *
     * We generally recommend altering this or temperature but not both.
     *
     * @return the topP value.
     */

    public Double getTopP() {
        return this.topP;
    }

    /**
     * Set the topP property: An alternative to sampling with temperature, called nucleus sampling, where the model
     * considers the results of the tokens with top_p probability mass.
     * So 0.1 means only the tokens comprising the top 10% probability mass are considered.
     *
     * We generally recommend altering this or temperature but not both.
     *
     * @param topP the topP value to set.
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setTopP(Double topP) {
        this.topP = topP;
        return this;
    }

    /**
     * Get the responseFormat property: The response format of the tool calls used by this assistant.
     *
     * @return the responseFormat value.
     */
    public AssistantsApiResponseFormatOption getResponseFormat() {
        return AssistantsApiResponseFormatOption.fromBinaryData(this.responseFormat);
    }

    /**
     * Set the responseFormat property: The response format of the tool calls used by this assistant.
     *
     * @param responseFormat the responseFormat value to set.
     * @return the AssistantCreationOptions object itself.
     */
    public AssistantCreationOptions setResponseFormat(AssistantsApiResponseFormatOption responseFormat) {
        if (responseFormat.getFormat() != null) {
            this.responseFormat = BinaryData.fromObject(responseFormat.getFormat());
        } else if (responseFormat.getMode() != null) {
            this.responseFormat = BinaryData.fromObject(responseFormat.getMode());
        } else {
            this.responseFormat = null;
        }
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
     * @return the AssistantCreationOptions object itself.
     */

    public AssistantCreationOptions setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("model", this.model);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeStringField("instructions", this.instructions);
        jsonWriter.writeArrayField("tools", this.tools, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("tool_resources", this.toolResources);
        jsonWriter.writeNumberField("temperature", this.temperature);
        jsonWriter.writeNumberField("top_p", this.topP);
        if (this.responseFormat != null) {
            jsonWriter.writeUntypedField("response_format", this.responseFormat.toObject(Object.class));
        }
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AssistantCreationOptions from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AssistantCreationOptions if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AssistantCreationOptions.
     */

    public static AssistantCreationOptions fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String model = null;
            String name = null;
            String description = null;
            String instructions = null;
            List<ToolDefinition> tools = null;
            CreateToolResourcesOptions toolResources = null;
            Double temperature = null;
            Double topP = null;
            BinaryData responseFormat = null;
            Map<String, String> metadata = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("model".equals(fieldName)) {
                    model = reader.getString();
                } else if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("description".equals(fieldName)) {
                    description = reader.getString();
                } else if ("instructions".equals(fieldName)) {
                    instructions = reader.getString();
                } else if ("tools".equals(fieldName)) {
                    tools = reader.readArray(reader1 -> ToolDefinition.fromJson(reader1));
                } else if ("tool_resources".equals(fieldName)) {
                    toolResources = CreateToolResourcesOptions.fromJson(reader);
                } else if ("temperature".equals(fieldName)) {
                    temperature = reader.getNullable(JsonReader::getDouble);
                } else if ("top_p".equals(fieldName)) {
                    topP = reader.getNullable(JsonReader::getDouble);
                } else if ("response_format".equals(fieldName)) {
                    responseFormat
                        = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("metadata".equals(fieldName)) {
                    metadata = reader.readMap(reader1 -> reader1.getString());
                } else {
                    reader.skipChildren();
                }
            }
            AssistantCreationOptions deserializedAssistantCreationOptions = new AssistantCreationOptions(model);
            deserializedAssistantCreationOptions.name = name;
            deserializedAssistantCreationOptions.description = description;
            deserializedAssistantCreationOptions.instructions = instructions;
            deserializedAssistantCreationOptions.tools = tools;
            deserializedAssistantCreationOptions.toolResources = toolResources;
            deserializedAssistantCreationOptions.temperature = temperature;
            deserializedAssistantCreationOptions.topP = topP;
            deserializedAssistantCreationOptions.responseFormat = responseFormat;
            deserializedAssistantCreationOptions.metadata = metadata;
            return deserializedAssistantCreationOptions;
        });
    }
}
