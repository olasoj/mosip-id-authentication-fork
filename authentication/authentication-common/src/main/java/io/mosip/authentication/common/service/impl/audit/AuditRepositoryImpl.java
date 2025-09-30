package io.mosip.authentication.common.service.impl.audit;

import io.mosip.authentication.common.service.repository.AuditRepository;
import io.mosip.authentication.core.audit.dto.Event;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuditRepositoryImpl implements AuditRepository {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private final ConcurrentMap<Integer, Event> events = new ConcurrentHashMap<>();

    @Override
    public Event recordEvent(Event event) {

        Assert.notNull(event, "Event must not be null");

        int id = counter.incrementAndGet();
        event.setEventId(id);

        events.put(id, event);
        return event;
    }
}
