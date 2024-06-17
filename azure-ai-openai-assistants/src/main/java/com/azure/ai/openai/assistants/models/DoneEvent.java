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
 * Terminal event indicating the successful end of a stream.
 */
public final class DoneEvent implements ExpandableEnum<DoneEvent> {
    private static final Map<String, DoneEvent> VALUES = new ConcurrentHashMap<>();

    private final String caseSensitive;
    private final String caseInsensitive;

    private DoneEvent(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public DoneEvent getValue() {
        return DoneEvent.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link DoneEvent} values.
     *
     * @return The known {@link DoneEvent} values.
     */
    public static Collection<DoneEvent> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link DoneEvent} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link DoneEvent} of the provided name, or {@code null} if {@code name} was
     * {@code null}.
     */
    public static DoneEvent fromString(String name) {
        if (name == null) {
            return null;
        }

        DoneEvent exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, DoneEvent::new);
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

        if (!(obj instanceof DoneEvent)) {
            return false;
        }

        DoneEvent other = (DoneEvent) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }
    /**
     * Event sent when the stream is done.
     */
    
    public static final DoneEvent DONE = fromString("done");

}
