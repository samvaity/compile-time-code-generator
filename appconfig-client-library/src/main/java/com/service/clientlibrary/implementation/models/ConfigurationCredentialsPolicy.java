// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.service.clientlibrary.implementation.models;

import io.clientcore.core.http.models.HttpRequest;
import io.clientcore.core.http.models.HttpResponse;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.http.pipeline.HttpPipelineNextPolicy;
import io.clientcore.core.http.pipeline.HttpPipelinePolicy;

import java.util.Objects;

/**
 * A policy that authenticates requests with Azure App Configuration service. The content added by this policy
 * is leveraged in {@link ConfigurationClientCredentials} to generate the correct "Authorization" header value.
 *
 * @see ConfigurationClientCredentials
 * @see com.service.clientlibrary.AppConfigClientBuilder
 */
public final class ConfigurationCredentialsPolicy implements HttpPipelinePolicy {
    // "Host", "Date", and "x-ms-content-sha256" are required to generate "Authorization" value in
    // ConfigurationClientCredentials.

    private final ConfigurationClientCredentials credentials;

    /**
     * Creates an instance that is able to apply a {@link ConfigurationClientCredentials} credential to a request in the
     * pipeline.
     *
     * @param credentials the credential information to authenticate to Azure App Configuration service
     * @throws NullPointerException If {@code credential} is {@code null}.
     */
    public ConfigurationCredentialsPolicy(ConfigurationClientCredentials credentials) {
        Objects.requireNonNull(credentials, "'credential' can not be a null value.");
        this.credentials = credentials;
    }
    /**
     * Adds the required headers to authenticate a request to Azure App Configuration service.
     *
     * @param httpRequest The request context
     * @param next The next HTTP pipeline policy to process the {@code context's} request after this policy
     *     completes.
     * @return A {@link HttpResponse} that will arrive synchronously.
     */
    @Override
    public Response<?> process(HttpRequest httpRequest, HttpPipelineNextPolicy next) {
        credentials.setAuthorizationHeaders(httpRequest);

        return next.process();
    }
}

