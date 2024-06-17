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
 * A set of reasons describing why a message is marked as incomplete.
 */
public final class MessageIncompleteDetailsReason implements ExpandableEnum<MessageIncompleteDetailsReason> {
    private static final Map<String, MessageIncompleteDetailsReason> VALUES = new ConcurrentHashMap<>();

    private final String caseSensitive;
    private final String caseInsensitive;

    private MessageIncompleteDetailsReason(String name) {
        this.caseSensitive = name;
        this.caseInsensitive = name.toLowerCase();
    }

    @Override
    public MessageIncompleteDetailsReason getValue() {
        return MessageIncompleteDetailsReason.fromString(caseSensitive);
    }

    /**
     * Gets all known {@link MessageIncompleteDetailsReason} values.
     *
     * @return The known {@link MessageIncompleteDetailsReason} values.
     */
    public static Collection<MessageIncompleteDetailsReason> values() {
        return VALUES.values();
    }

    /**
     * Creates or finds a {@link MessageIncompleteDetailsReason} for the passed {@code name}.
     *
     * <p>{@code null} will be returned if {@code name} is {@code null}.</p>
     *
     * @param name A name to look for.
     *
     * @return The corresponding {@link MessageIncompleteDetailsReason} of the provided name, or {@code null} if {@code name} was
     * {@code null}.
     */
    public static MessageIncompleteDetailsReason fromString(String name) {
        if (name == null) {
            return null;
        }

        MessageIncompleteDetailsReason exceptionType = VALUES.get(name);

        if (exceptionType != null) {
            return exceptionType;
        }

        return VALUES.computeIfAbsent(name, MessageIncompleteDetailsReason::new);
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

        if (!(obj instanceof MessageIncompleteDetailsReason)) {
            return false;
        }

        MessageIncompleteDetailsReason other = (MessageIncompleteDetailsReason) obj;

        return Objects.equals(caseInsensitive, other.caseInsensitive);
    }

    /**
     * The run generating the message was terminated due to content filter flagging.
     */
    
    public static final MessageIncompleteDetailsReason CONTENT_FILTER = fromString("content_filter");

    /**
     * The run generating the message exhausted available tokens before completion.
     */
    
    public static final MessageIncompleteDetailsReason MAX_TOKENS = fromString("max_tokens");

    /**
     * The run generating the message was cancelled before completion.
     */
    
    public static final MessageIncompleteDetailsReason RUN_CANCELLED = fromString("run_cancelled");

    /**
     * The run generating the message failed.
     */
    
    public static final MessageIncompleteDetailsReason RUN_FAILED = fromString("run_failed");



    /**
     * The run generating the message expired.
     */
    
    public static final MessageIncompleteDetailsReason RUN_EXPIRED = fromString("run_expired");
}
