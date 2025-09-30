package io.mosip.authentication.core.audit.dto;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "timestamp",
        "eventId"
})
@Data
public class AuditResponse implements Serializable {

    //@Serial
    private static final long serialVersionUID = -6598322844539752951L;
    @JsonIgnore
    private final Map<String, Object> additionalProperties = new LinkedHashMap<>();
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("eventId")
    private String eventId;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
