package io.mosip.authentication.core.health.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "status",
        "timestamp",
        "someConfig",
        "metadata"
})
@Data
public class HealthDetailsResponse implements Serializable {

    private static final long serialVersionUID = 6798549695119061947L;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
    @JsonProperty("status")
    private String status;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("someConfig")
    private String someConfig;
    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPropertyOrder({
            "service-name",
            "version",
            "environment"
    })

    @Data
    public static class Metadata implements Serializable {

        private static final long serialVersionUID = -781085023521159456L;
        @JsonIgnore
        private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
        @JsonProperty("serviceName")
        private String serviceName;
        @JsonProperty("version")
        private String version;
        @JsonProperty("environment")
        private String environment;

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

}
