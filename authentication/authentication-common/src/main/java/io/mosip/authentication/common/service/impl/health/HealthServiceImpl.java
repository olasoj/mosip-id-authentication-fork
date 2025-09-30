package io.mosip.authentication.common.service.impl.health;

import io.mosip.authentication.core.health.dto.HealthDetailsResponse;
import io.mosip.authentication.core.health.dto.HealthStatus;
import io.mosip.authentication.core.health.dto.MosipConfig;
import io.mosip.authentication.core.spi.health.service.HealthService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class HealthServiceImpl implements HealthService {

    private final MosipConfig mosipConfig;

    private static final Random randomNumberGenerator = new Random();

    public HealthServiceImpl(MosipConfig mosipConfig) {
        this.mosipConfig = mosipConfig;
    }

    @Override
    public HealthDetailsResponse getHealthDetails() {

        HealthDetailsResponse healthDetailsResponse = new HealthDetailsResponse();

        int random = randomNumberGenerator.nextInt();
        String status = (random % 2 == 0) ? HealthStatus.UP.name() : HealthStatus.DOWN.name();
        healthDetailsResponse.setStatus(status);

        healthDetailsResponse.setTimestamp(Instant.now().toString());
        healthDetailsResponse.setSomeConfig(mosipConfig.getSomeConfig());

        HealthDetailsResponse.Metadata metadata = new HealthDetailsResponse.Metadata();

        metadata.setServiceName("serviceName");
        metadata.setEnvironment("qa");
        metadata.setVersion("3.10.4");

        healthDetailsResponse.setMetadata(metadata);

        return healthDetailsResponse;
    }
}
