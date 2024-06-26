// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.service.clientlibrary.models;

import io.clientcore.core.annotation.Metadata;
import io.clientcore.core.annotation.TypeConditions;
import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;

/**
 * The TodoPagePagination model.
 */
@Metadata(conditions = { TypeConditions.IMMUTABLE })
public final class TodoPagePagination implements JsonSerializable<TodoPagePagination> {
    /*
     * The number of items returned in this page
     */
    @Metadata(generated = true)
    private final int pageSize;

    /*
     * The total number of items
     */
    @Metadata(generated = true)
    private final int totalSize;

    /*
     * A link to the previous page, if it exists
     */
    @Metadata(generated = true)
    private String prevLink;

    /*
     * A link to the next page, if it exists
     */
    @Metadata(generated = true)
    private String nextLink;

    /**
     * Creates an instance of TodoPagePagination class.
     * 
     * @param pageSize the pageSize value to set.
     * @param totalSize the totalSize value to set.
     */
    @Metadata(generated = true)
    private TodoPagePagination(int pageSize, int totalSize) {
        this.pageSize = pageSize;
        this.totalSize = totalSize;
    }

    /**
     * Get the pageSize property: The number of items returned in this page.
     * 
     * @return the pageSize value.
     */
    @Metadata(generated = true)
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * Get the totalSize property: The total number of items.
     * 
     * @return the totalSize value.
     */
    @Metadata(generated = true)
    public int getTotalSize() {
        return this.totalSize;
    }

    /**
     * Get the prevLink property: A link to the previous page, if it exists.
     * 
     * @return the prevLink value.
     */
    @Metadata(generated = true)
    public String getPrevLink() {
        return this.prevLink;
    }

    /**
     * Get the nextLink property: A link to the next page, if it exists.
     * 
     * @return the nextLink value.
     */
    @Metadata(generated = true)
    public String getNextLink() {
        return this.nextLink;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(generated = true)
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("pageSize", this.pageSize);
        jsonWriter.writeIntField("totalSize", this.totalSize);
        jsonWriter.writeStringField("prevLink", this.prevLink);
        jsonWriter.writeStringField("nextLink", this.nextLink);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TodoPagePagination from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TodoPagePagination if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TodoPagePagination.
     */
    @Metadata(generated = true)
    public static TodoPagePagination fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int pageSize = 0;
            int totalSize = 0;
            String prevLink = null;
            String nextLink = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("pageSize".equals(fieldName)) {
                    pageSize = reader.getInt();
                } else if ("totalSize".equals(fieldName)) {
                    totalSize = reader.getInt();
                } else if ("prevLink".equals(fieldName)) {
                    prevLink = reader.getString();
                } else if ("nextLink".equals(fieldName)) {
                    nextLink = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            TodoPagePagination deserializedTodoPagePagination = new TodoPagePagination(pageSize, totalSize);
            deserializedTodoPagePagination.prevLink = prevLink;
            deserializedTodoPagePagination.nextLink = nextLink;

            return deserializedTodoPagePagination;
        });
    }
}
