package io.mosip.authentication.core.audit.dto;

import lombok.Data;

@Data
public class Event {

    private Integer eventId;
    private String eventType;
    private String userId;
    private String description;
}
