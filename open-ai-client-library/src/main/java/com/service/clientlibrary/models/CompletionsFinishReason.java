package com.service.clientlibrary.models;

import io.clientcore.core.util.ExpandableEnum;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Representation of the manner in which a completions response concluded.
 */
public final class CompletionsFinishReason
    implements ExpandableEnum<CompletionsFinishReason> {
    private static final Map<String, CompletionsFinishReason> VALUES = new ConcurrentHashMap<>();
    public static final CompletionsFinishReason STOPPED = fromString("json");
    public static final CompletionsFinishReason TOKEN_LIMIT_REACHED = fromString("length");
    public static final CompletionsFinishReason CONTENT_FILTERED = fromString("content_filter");
    public static final CompletionsFinishReason FUNCTION_CALL = fromString("function_call");
    public static final CompletionsFinishReason TOOL_CALLS = fromString("tool_calls");

    private final String caseSensitive;
    private final String caseInsensitive;

    private CompletionsFinishReason(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public CompletionsFinishReason getValue() {
        return CompletionsFinishReason.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link CompletionsFinishReason} values.
     *
     * @return The known {@link CompletionsFinishReason} values.
     */
    public static Collection<CompletionsFinishReason> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link CompletionsFinishReason} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link CompletionsFinishReason} of the provided name, or {@code null} if {@code name}
     * was {@code null}.
     */
    public static CompletionsFinishReason fromString(String name) {
        if (name == null) {
            return null;
        }

        CompletionsFinishReason exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, CompletionsFinishReason::new);
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

        if (!(obj instanceof CompletionsFinishReason)) {
            return false;
        }

        CompletionsFinishReason other = (CompletionsFinishReason) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }
}

