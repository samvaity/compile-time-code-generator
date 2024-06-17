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
 * Terminal event indicating a server side error while streaming.
 */
public final class ErrorEvent implements ExpandableEnum<ErrorEvent> {
    private static final Map<String, ErrorEvent> VALUES = new ConcurrentHashMap<>();

    private final String caseSensitive;
    private final String caseInsensitive;

    private ErrorEvent(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public ErrorEvent getValue() {
        return ErrorEvent.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link ErrorEvent} values.
     *
     * @return The known {@link ErrorEvent} values.
     */
    public static Collection<ErrorEvent> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link ErrorEvent} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link ErrorEvent} of the provided name, or {@code null} if {@code name} was
     * {@code null}.
     */
    public static ErrorEvent fromString(String name) {
        if (name == null) {
            return null;
        }

        ErrorEvent exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, ErrorEvent::new);
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

        if (!(obj instanceof ErrorEvent)) {
            return false;
        }

        ErrorEvent other = (ErrorEvent) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }
    /**
     * Event sent when an error occurs, such as an internal server error or a timeout.
     */

    public static final ErrorEvent ERROR = fromString("error");

    
}
