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
 * Possible values for the status of an assistant thread run.
 */
public final class RunStatus implements ExpandableEnum<RunStatus> {
    private static final Map<String, RunStatus> VALUES = new ConcurrentHashMap<>();

    private final String caseSensitive;
    private final String caseInsensitive;

    private RunStatus(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public RunStatus getValue() {
        return RunStatus.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link RunStatus} values.
     *
     * @return The known {@link RunStatus} values.
     */
    public static Collection<RunStatus> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link RunStatus} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link RunStatus} of the provided name, or {@code null} if {@code name} was
     * {@code null}.
     */
    public static RunStatus fromString(String name) {
        if (name == null) {
            return null;
        }

        RunStatus exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, RunStatus::new);
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

        if (!(obj instanceof RunStatus)) {
            return false;
        }

        RunStatus other = (RunStatus) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }

    /**
     * Represents a run that is queued to start.
     */
    
    public static final RunStatus QUEUED = fromString("queued");

    /**
     * Represents a run that is in progress.
     */
    
    public static final RunStatus IN_PROGRESS = fromString("in_progress");

    /**
     * Represents a run that needs another operation, such as tool output submission, to continue.
     */
    
    public static final RunStatus REQUIRES_ACTION = fromString("requires_action");

    /**
     * Represents a run that is in the process of cancellation.
     */
    
    public static final RunStatus CANCELLING = fromString("cancelling");

    /**
     * Represents a run that has been cancelled.
     */
    
    public static final RunStatus CANCELLED = fromString("cancelled");

    /**
     * Represents a run that failed.
     */
    
    public static final RunStatus FAILED = fromString("failed");

    /**
     * Represents a run that successfully completed.
     */
    
    public static final RunStatus COMPLETED = fromString("completed");

    /**
     * Represents a run that expired before it could otherwise finish.
     */
    
    public static final RunStatus EXPIRED = fromString("expired");


}
