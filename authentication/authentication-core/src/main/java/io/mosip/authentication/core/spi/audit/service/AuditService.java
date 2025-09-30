package io.mosip.authentication.core.spi.audit.service;

import io.mosip.authentication.core.audit.dto.AuditRequest;
import io.mosip.authentication.core.audit.dto.AuditResponse;

public interface AuditService {
    AuditResponse audit(AuditRequest auditRequest);
}
