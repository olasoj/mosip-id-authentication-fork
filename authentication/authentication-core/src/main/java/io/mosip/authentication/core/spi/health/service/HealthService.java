package io.mosip.authentication.core.spi.health.service;

import io.mosip.authentication.core.health.dto.HealthDetailsResponse;

public interface HealthService {
    HealthDetailsResponse getHealthDetails();
}
