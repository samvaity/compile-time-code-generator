// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.ai.openai.assistants;

import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;
import java.util.List;

public class FunctionEnumParameter implements JsonSerializable<FunctionEnumParameter> {

    private String type = "string";
    private List<String> enumValues;

    public FunctionEnumParameter(List<String> enumValues) {
        this.enumValues = enumValues;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", "string");
        jsonWriter.writeArrayField("enum",  this.enumValues, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    public static FunctionEnumParameter fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            List<String> enumValues = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("enum".equals(fieldName)) {
                    enumValues = reader.readArray(reader1 -> reader1.getString());
                }
            }
            return new FunctionEnumParameter(enumValues);
        });
    }
}
