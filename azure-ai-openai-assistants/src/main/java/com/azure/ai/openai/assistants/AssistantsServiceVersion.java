// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.azure.ai.openai.assistants;


import io.clientcore.core.http.models.ServiceVersion;

/**
 * Service version of AssistantsClient.
 */
public enum AssistantsServiceVersion implements ServiceVersion {
    /**
     * Enum value 2024-02-15-preview.
     */
    V2024_02_15_PREVIEW("2024-02-15-preview"),

    /**
     * Enum value 2024-05-01-preview.
     */
    V2024_05_01_PREVIEW("2024-05-01-preview");

    private final String version;

    AssistantsServiceVersion(String version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     * 
     * @return The latest {@link AssistantsServiceVersion}.
     */
    public static AssistantsServiceVersion getLatest() {
        return V2024_05_01_PREVIEW;
    }
}
