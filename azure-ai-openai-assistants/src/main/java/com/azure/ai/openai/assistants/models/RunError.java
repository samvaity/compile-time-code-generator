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
 * The details of an error as encountered by an assistant thread run.
 */

public final class RunError implements JsonSerializable<RunError> {

    /*
     * The status for the error.
     */
    
    private final String code;

    /*
     * The human-readable text associated with the error.
     */
    
    private final String message;

    /**
     * Creates an instance of RunError class.
     *
     * @param code the code value to set.
     * @param message the message value to set.
     */
    
    private RunError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get the code property: The status for the error.
     *
     * @return the code value.
     */
    
    public String getCode() {
        return this.code;
    }

    /**
     * Get the message property: The human-readable text associated with the error.
     *
     * @return the message value.
     */
    
    public String getMessage() {
        return this.message;
    }

    /**
     * {@inheritDoc}
     */
    
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RunError from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of RunError if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RunError.
     */
    
    public static RunError fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String code = null;
            String message = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("code".equals(fieldName)) {
                    code = reader.getString();
                } else if ("message".equals(fieldName)) {
                    message = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new RunError(code, message);
        });
    }
}
