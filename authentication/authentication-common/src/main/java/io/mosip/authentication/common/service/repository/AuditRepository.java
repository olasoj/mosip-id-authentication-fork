package io.mosip.authentication.common.service.repository;

import io.mosip.authentication.core.audit.dto.Event;

public interface AuditRepository {
    Event recordEvent(Event event);
}
