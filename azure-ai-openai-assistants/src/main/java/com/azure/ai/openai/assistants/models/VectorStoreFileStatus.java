// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.azure.ai.openai.assistants.models;


import io.clientcore.core.util.ExpandableEnum;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Vector store file status.
 */
public final class VectorStoreFileStatus implements ExpandableEnum<VectorStoreFileStatus> {
    private static final Map<String, VectorStoreFileStatus> VALUES = new ConcurrentHashMap<>();

    private final String caseSensitive;
    private final String caseInsensitive;

    private VectorStoreFileStatus(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public VectorStoreFileStatus getValue() {
        return VectorStoreFileStatus.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link VectorStoreFileStatus} values.
     *
     * @return The known {@link VectorStoreFileStatus} values.
     */
    public static Collection<VectorStoreFileStatus> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link VectorStoreFileStatus} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link VectorStoreFileStatus} of the provided name, or {@code null} if {@code name} was
     * {@code null}.
     */
    public static VectorStoreFileStatus fromString(String name) {
        if (name == null) {
            return null;
        }

        VectorStoreFileStatus exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, VectorStoreFileStatus::new);
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

        if (!(obj instanceof VectorStoreFileStatus)) {
            return false;
        }

        VectorStoreFileStatus other = (VectorStoreFileStatus) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }
    /**
     * The file is currently being processed.
     */
    
    public static final VectorStoreFileStatus IN_PROGRESS = fromString("in_progress");

    /**
     * The file has been successfully processed.
     */
    
    public static final VectorStoreFileStatus COMPLETED = fromString("completed");

    /**
     * The file has failed to process.
     */
    
    public static final VectorStoreFileStatus FAILED = fromString("failed");

    /**
     * The file was cancelled.
     */
    
    public static final VectorStoreFileStatus CANCELLED = fromString("cancelled");
}
