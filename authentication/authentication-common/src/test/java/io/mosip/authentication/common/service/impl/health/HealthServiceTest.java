package io.mosip.authentication.common.service.impl.health;

import io.mosip.authentication.core.health.dto.HealthDetailsResponse;
import io.mosip.authentication.core.health.dto.MosipConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HealthServiceTest {

    @Mock
    private MosipConfig mosipConfig;

    @InjectMocks
    private HealthServiceImpl healthService;

    @Test
    public void testGetHealthDetails_StatusIsUpOrDown() {

        when(mosipConfig.getSomeConfig()).thenReturn("test-config");

        HealthDetailsResponse healthDetailsResponse = healthService.getHealthDetails();
        assertNotNull(healthDetailsResponse);

        assertEquals("test-config", healthDetailsResponse.getSomeConfig());
        verify(mosipConfig, atLeastOnce()).getSomeConfig();


        assertNotNull(healthDetailsResponse.getMetadata());
        assertEquals("serviceName", healthDetailsResponse.getMetadata().getServiceName());
        assertEquals("qa", healthDetailsResponse.getMetadata().getEnvironment());
        assertEquals("3.10.4", healthDetailsResponse.getMetadata().getVersion());

        // Run multiple times to cover randomness
        for (int i = 0; i < 10; i++) {
            HealthDetailsResponse response = healthService.getHealthDetails();
            assertTrue(
                    response.getStatus().equals("UP") || response.getStatus().equals("DOWN"),
                    "Status should be either UP or DOWN"
            );
        }
    }

}
