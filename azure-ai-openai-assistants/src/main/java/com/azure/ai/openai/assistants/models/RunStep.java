// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.models;


import io.clientcore.core.json.JsonReader;
import io.clientcore.core.json.JsonSerializable;
import io.clientcore.core.json.JsonToken;
import io.clientcore.core.json.JsonWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * Detailed information about a single step of an assistant thread run.
 */

public final class RunStep implements JsonSerializable<RunStep> {

    /*
     * The identifier, which can be referenced in API endpoints.
     */

    private final String id;

    /*
     * The object type, which is always 'thread.run.step'.
     */

    private final String object = "thread.run.step";

    /*
     * The type of run step, which can be either message_creation or tool_calls.
     */

    private final RunStepType type;

    /*
     * The ID of the assistant associated with the run step.
     */

    private final String assistantId;

    /*
     * The ID of the thread that was run.
     */

    private final String threadId;

    /*
     * The ID of the run that this run step is a part of.
     */

    private final String runId;

    /*
     * The status of this run step.
     */

    private final RunStepStatus status;

    /*
     * The details for this run step.
     */

    private final RunStepDetails stepDetails;

    /*
     * If applicable, information about the last error encountered by this run step.
     */

    private final RunStepError lastError;

    /*
     * The Unix timestamp, in seconds, representing when this object was created.
     */

    private final long createdAt;

    /*
     * The Unix timestamp, in seconds, representing when this item expired.
     */

    private final Long expiredAt;

    /*
     * The Unix timestamp, in seconds, representing when this completed.
     */

    private final Long completedAt;

    /*
     * The Unix timestamp, in seconds, representing when this was cancelled.
     */

    private final Long cancelledAt;

    /*
     * The Unix timestamp, in seconds, representing when this failed.
     */

    private final Long failedAt;

    /*
     * A set of up to 16 key/value pairs that can be attached to an object, used for storing additional information
     * about that object in a structured format. Keys may be up to 64 characters in length and values may be up to 512
     * characters in length.
     */

    private final Map<String, String> metadata;

    /**
     * Get the id property: The identifier, which can be referenced in API endpoints.
     *
     * @return the id value.
     */

    public String getId() {
        return this.id;
    }

    /**
     * Get the object property: The object type, which is always 'thread.run.step'.
     *
     * @return the object value.
     */

    public String getObject() {
        return this.object;
    }

    /**
     * Get the type property: The type of run step, which can be either message_creation or tool_calls.
     *
     * @return the type value.
     */

    public RunStepType getType() {
        return this.type;
    }

    /**
     * Get the assistantId property: The ID of the assistant associated with the run step.
     *
     * @return the assistantId value.
     */

    public String getAssistantId() {
        return this.assistantId;
    }

    /**
     * Get the threadId property: The ID of the thread that was run.
     *
     * @return the threadId value.
     */

    public String getThreadId() {
        return this.threadId;
    }

    /**
     * Get the runId property: The ID of the run that this run step is a part of.
     *
     * @return the runId value.
     */

    public String getRunId() {
        return this.runId;
    }

    /**
     * Get the status property: The status of this run step.
     *
     * @return the status value.
     */

    public RunStepStatus getStatus() {
        return this.status;
    }

    /**
     * Get the stepDetails property: The details for this run step.
     *
     * @return the stepDetails value.
     */

    public RunStepDetails getStepDetails() {
        return this.stepDetails;
    }

    /**
     * Get the lastError property: If applicable, information about the last error encountered by this run step.
     *
     * @return the lastError value.
     */

    public RunStepError getLastError() {
        return this.lastError;
    }

    /**
     * Get the createdAt property: The Unix timestamp, in seconds, representing when this object was created.
     *
     * @return the createdAt value.
     */

    public OffsetDateTime getCreatedAt() {
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.createdAt), ZoneOffset.UTC);
    }

    /**
     * Get the expiredAt property: The Unix timestamp, in seconds, representing when this item expired.
     *
     * @return the expiredAt value.
     */

    public OffsetDateTime getExpiredAt() {
        if (this.expiredAt == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.expiredAt), ZoneOffset.UTC);
    }

    /**
     * Get the completedAt property: The Unix timestamp, in seconds, representing when this completed.
     *
     * @return the completedAt value.
     */

    public OffsetDateTime getCompletedAt() {
        if (this.completedAt == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.completedAt), ZoneOffset.UTC);
    }

    /**
     * Get the cancelledAt property: The Unix timestamp, in seconds, representing when this was cancelled.
     *
     * @return the cancelledAt value.
     */

    public OffsetDateTime getCancelledAt() {
        if (this.cancelledAt == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.cancelledAt), ZoneOffset.UTC);
    }

    /**
     * Get the failedAt property: The Unix timestamp, in seconds, representing when this failed.
     *
     * @return the failedAt value.
     */

    public OffsetDateTime getFailedAt() {
        if (this.failedAt == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.failedAt), ZoneOffset.UTC);
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
     * {@inheritDoc}
     */

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeStringField("object", this.object);
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("assistant_id", this.assistantId);
        jsonWriter.writeStringField("thread_id", this.threadId);
        jsonWriter.writeStringField("run_id", this.runId);
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeJsonField("step_details", this.stepDetails);
        jsonWriter.writeJsonField("last_error", this.lastError);
        jsonWriter.writeLongField("created_at", this.createdAt);
        jsonWriter.writeNumberField("expired_at", this.expiredAt);
        jsonWriter.writeNumberField("completed_at", this.completedAt);
        jsonWriter.writeNumberField("cancelled_at", this.cancelledAt);
        jsonWriter.writeNumberField("failed_at", this.failedAt);
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeString(element));
        jsonWriter.writeJsonField("usage", this.usage);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RunStep from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of RunStep if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RunStep.
     */

    public static RunStep fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            RunStepType type = null;
            String assistantId = null;
            String threadId = null;
            String runId = null;
            RunStepStatus status = null;
            RunStepDetails stepDetails = null;
            RunStepError lastError = null;
            OffsetDateTime createdAt = null;
            OffsetDateTime expiredAt = null;
            OffsetDateTime completedAt = null;
            OffsetDateTime cancelledAt = null;
            OffsetDateTime failedAt = null;
            Map<String, String> metadata = null;
            RunStepCompletionUsage usage = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("type".equals(fieldName)) {
                    type = RunStepType.fromString(reader.getString());
                } else if ("assistant_id".equals(fieldName)) {
                    assistantId = reader.getString();
                } else if ("thread_id".equals(fieldName)) {
                    threadId = reader.getString();
                } else if ("run_id".equals(fieldName)) {
                    runId = reader.getString();
                } else if ("status".equals(fieldName)) {
                    status = RunStepStatus.fromString(reader.getString());
                } else if ("step_details".equals(fieldName)) {
                    stepDetails = RunStepDetails.fromJson(reader);
                } else if ("last_error".equals(fieldName)) {
                    lastError = RunStepError.fromJson(reader);
                } else if ("created_at".equals(fieldName)) {
                    createdAt = OffsetDateTime.ofInstant(Instant.ofEpochSecond(reader.getLong()), ZoneOffset.UTC);
                } else if ("expired_at".equals(fieldName)) {
                    Long expiredAtHolder = reader.getNullable(JsonReader::getLong);
                    if (expiredAtHolder != null) {
                        expiredAt = OffsetDateTime.ofInstant(Instant.ofEpochSecond(expiredAtHolder), ZoneOffset.UTC);
                    }
                } else if ("completed_at".equals(fieldName)) {
                    Long completedAtHolder = reader.getNullable(JsonReader::getLong);
                    if (completedAtHolder != null) {
                        completedAt
                            = OffsetDateTime.ofInstant(Instant.ofEpochSecond(completedAtHolder), ZoneOffset.UTC);
                    }
                } else if ("cancelled_at".equals(fieldName)) {
                    Long cancelledAtHolder = reader.getNullable(JsonReader::getLong);
                    if (cancelledAtHolder != null) {
                        cancelledAt
                            = OffsetDateTime.ofInstant(Instant.ofEpochSecond(cancelledAtHolder), ZoneOffset.UTC);
                    }
                } else if ("failed_at".equals(fieldName)) {
                    Long failedAtHolder = reader.getNullable(JsonReader::getLong);
                    if (failedAtHolder != null) {
                        failedAt = OffsetDateTime.ofInstant(Instant.ofEpochSecond(failedAtHolder), ZoneOffset.UTC);
                    }
                } else if ("metadata".equals(fieldName)) {
                    metadata = reader.readMap(reader1 -> reader1.getString());
                } else if ("usage".equals(fieldName)) {
                    usage = RunStepCompletionUsage.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            RunStep deserializedRunStep = new RunStep(id, type, assistantId, threadId, runId, status, stepDetails,
                lastError, createdAt, expiredAt, completedAt, cancelledAt, failedAt, metadata);
            deserializedRunStep.usage = usage;
            return deserializedRunStep;
        });
    }

    /*
     * Usage statistics related to the run step. This value will be `null` while the run step's status is `in_progress`.
     */

    private RunStepCompletionUsage usage;

    /**
     * Get the usage property: Usage statistics related to the run step. This value will be `null` while the run step's
     * status is `in_progress`.
     *
     * @return the usage value.
     */

    public RunStepCompletionUsage getUsage() {
        return this.usage;
    }

    /**
     * Creates an instance of RunStep class.
     *
     * @param id the id value to set.
     * @param type the type value to set.
     * @param assistantId the assistantId value to set.
     * @param threadId the threadId value to set.
     * @param runId the runId value to set.
     * @param status the status value to set.
     * @param stepDetails the stepDetails value to set.
     * @param lastError the lastError value to set.
     * @param createdAt the createdAt value to set.
     * @param expiredAt the expiredAt value to set.
     * @param completedAt the completedAt value to set.
     * @param cancelledAt the cancelledAt value to set.
     * @param failedAt the failedAt value to set.
     * @param metadata the metadata value to set.
     */

    private RunStep(String id, RunStepType type, String assistantId, String threadId, String runId,
        RunStepStatus status, RunStepDetails stepDetails, RunStepError lastError, OffsetDateTime createdAt,
        OffsetDateTime expiredAt, OffsetDateTime completedAt, OffsetDateTime cancelledAt, OffsetDateTime failedAt,
        Map<String, String> metadata) {
        this.id = id;
        this.type = type;
        this.assistantId = assistantId;
        this.threadId = threadId;
        this.runId = runId;
        this.status = status;
        this.stepDetails = stepDetails;
        this.lastError = lastError;
        if (createdAt == null) {
            this.createdAt = 0L;
        } else {
            this.createdAt = createdAt.toEpochSecond();
        }
        if (expiredAt == null) {
            this.expiredAt = null;
        } else {
            this.expiredAt = expiredAt.toEpochSecond();
        }
        if (completedAt == null) {
            this.completedAt = null;
        } else {
            this.completedAt = completedAt.toEpochSecond();
        }
        if (cancelledAt == null) {
            this.cancelledAt = null;
        } else {
            this.cancelledAt = cancelledAt.toEpochSecond();
        }
        if (failedAt == null) {
            this.failedAt = null;
        } else {
            this.failedAt = failedAt.toEpochSecond();
        }
        this.metadata = metadata;
    }
}
