package io.mosip.authentication.common.service.impl.audit;

import io.mosip.authentication.common.service.repository.AuditRepository;
import io.mosip.authentication.core.spi.audit.service.AuditService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import io.mosip.authentication.core.audit.dto.AuditRequest;
import io.mosip.authentication.core.audit.dto.AuditResponse;
import io.mosip.authentication.core.audit.dto.Event;

import java.time.Instant;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    private static AuditResponse toAuditResponse(Event savedEvent) {

        AuditResponse auditResponse = new AuditResponse();

        auditResponse.setTimestamp(Instant.now().toString());
        auditResponse.setEventId(String.valueOf(savedEvent.getEventId()));
        return auditResponse;
    }

    private static Event getEvent(AuditRequest auditRequest) {

        Event event = new Event();

        event.setEventType(auditRequest.getEventType());
        event.setUserId(auditRequest.getUserId());
        event.setDescription(auditRequest.getDescription());
        return event;
    }

    @Override
    public AuditResponse audit(AuditRequest auditRequest) {

        Assert.notNull(auditRequest, "AuditRequest must not be null");
        Assert.hasText(auditRequest.getEventType(), "AuditRequest must have eventType");
        Assert.hasText(auditRequest.getUserId(), "AuditRequest must have userId");

        Event event = getEvent(auditRequest);
        Event savedEvent = auditRepository.recordEvent(event);

        return toAuditResponse(savedEvent);
    }

}
