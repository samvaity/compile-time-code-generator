// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.models;

import io.clientcore.core.util.ExpandableEnum;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Collection;

/**
 * The status of the vector store file batch.
 */
public final class VectorStoreFileBatchStatus implements ExpandableEnum<VectorStoreFileBatchStatus> {
    private static final Map<String, VectorStoreFileBatchStatus> VALUES = new ConcurrentHashMap<>();

    private final String caseSensitive;
    private final String caseInsensitive;

    private VectorStoreFileBatchStatus(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public VectorStoreFileBatchStatus getValue() {
        return VectorStoreFileBatchStatus.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link VectorStoreFileBatchStatus} values.
     *
     * @return The known {@link VectorStoreFileBatchStatus} values.
     */
    public static Collection<VectorStoreFileBatchStatus> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link VectorStoreFileBatchStatus} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link VectorStoreFileBatchStatus} of the provided name, or {@code null} if {@code name} was
     * {@code null}.
     */
    public static VectorStoreFileBatchStatus fromString(String name) {
        if (name == null) {
            return null;
        }

        VectorStoreFileBatchStatus exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, VectorStoreFileBatchStatus::new);
    }

    @Override
    public String toString() {
        return caseSensitive;
    }

    @Override
    public int hashCode() {
        return caseInsensitive.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VectorStoreFileBatchStatus)) {
            return false;
        }

        VectorStoreFileBatchStatus other = (VectorStoreFileBatchStatus) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }

    /**
     * The vector store is still processing this file batch.
     */
    
    public static final VectorStoreFileBatchStatus IN_PROGRESS = fromString("in_progress");

    /**
     * the vector store file batch is ready for use.
     */
    
    public static final VectorStoreFileBatchStatus COMPLETED = fromString("completed");

    /**
     * The vector store file batch was cancelled.
     */
    
    public static final VectorStoreFileBatchStatus CANCELLED = fromString("cancelled");

    /**
     * The vector store file batch failed to process.
     */
    
    public static final VectorStoreFileBatchStatus FAILED = fromString("failed");
}
