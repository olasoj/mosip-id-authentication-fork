package io.mosip.authentication.core.health.dto;

import junit.framework.TestCase;

import java.util.Map;

public class HealthDetailsResponseTest extends TestCase {

    public void testGettersAndSetters() {
        HealthDetailsResponse response = new HealthDetailsResponse();
        response.setStatus("UP");
        response.setTimestamp("123456789");
        response.setSomeConfig("test-config");

        HealthDetailsResponse.Metadata metadata = new HealthDetailsResponse.Metadata();
        metadata.setServiceName("serviceName");
        metadata.setVersion("1.0");
        metadata.setEnvironment("qa");

        response.setMetadata(metadata);

        assertEquals("UP", response.getStatus());
        assertEquals("123456789", response.getTimestamp());
        assertEquals("test-config", response.getSomeConfig());
        assertNotNull(response.getMetadata());
        assertEquals("serviceName", response.getMetadata().getServiceName());
        assertEquals("1.0", response.getMetadata().getVersion());
        assertEquals("qa", response.getMetadata().getEnvironment());
    }

    public void testAdditionalProperties() {
        HealthDetailsResponse response = new HealthDetailsResponse();
        response.setAdditionalProperty("key1", "value1");
        response.setAdditionalProperty("key2", 42);

        Map<String, Object> props = response.getAdditionalProperties();
        assertEquals(2, props.size());
        assertEquals("value1", props.get("key1"));
        assertEquals(42, props.get("key2"));
    }

    public void testMetadataAdditionalProperties() {
        HealthDetailsResponse.Metadata metadata = new HealthDetailsResponse.Metadata();
        metadata.setAdditionalProperty("metaKey1", true);
        metadata.setAdditionalProperty("metaKey2", 99.9);

        Map<String, Object> props = metadata.getAdditionalProperties();
        assertEquals(2, props.size());
        assertTrue((Boolean) props.get("metaKey1"));
        assertEquals(99.9, props.get("metaKey2"));
    }

    public void testEqualsAndHashCode() {
        HealthDetailsResponse r1 = new HealthDetailsResponse();
        r1.setStatus("UP");
        r1.setTimestamp("111");
        r1.setSomeConfig("cfg");

        HealthDetailsResponse r2 = new HealthDetailsResponse();
        r2.setStatus("UP");
        r2.setTimestamp("111");
        r2.setSomeConfig("cfg");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    public void testToString() {
        HealthDetailsResponse response = new HealthDetailsResponse();
        response.setStatus("UP");
        response.setTimestamp("123456");
        response.setSomeConfig("cfg");

        String toString = response.toString();
        assertTrue(toString.contains("UP"));
        assertTrue(toString.contains("123456"));
        assertTrue(toString.contains("cfg"));
    }
}
