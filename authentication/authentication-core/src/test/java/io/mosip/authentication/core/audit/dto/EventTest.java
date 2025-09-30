package io.mosip.authentication.core.audit.dto;

import junit.framework.TestCase;

import static org.junit.Assert.*;
public class EventTest extends TestCase {

    public void testGettersAndSetters() {
        Event event = new Event();
        event.setEventId(1);
        event.setEventType("LOGIN");
        event.setUserId("user123");
        event.setDescription("User logged in successfully");

        Integer i = 1;
        Integer eventId = event.getEventId();
        assertEquals(i, eventId);

        assertEquals("LOGIN", event.getEventType());
        assertEquals("user123", event.getUserId());
        assertEquals("User logged in successfully", event.getDescription());
    }

    public void testEqualsAndHashCode() {
        Event event1 = new Event();
        event1.setEventId(1);
        event1.setEventType("LOGIN");
        event1.setUserId("user123");
        event1.setDescription("User logged in successfully");

        Event event2 = new Event();
        event2.setEventId(1);
        event2.setEventType("LOGIN");
        event2.setUserId("user123");
        event2.setDescription("User logged in successfully");

        assertEquals(event1, event2);
        assertEquals(event1.hashCode(), event2.hashCode());
    }

    public void testNotEquals() {
        Event event1 = new Event();
        event1.setEventId(1);
        event1.setEventType("LOGIN");

        Event event2 = new Event();
        event2.setEventId(2);
        event2.setEventType("LOGOUT");

        assertNotEquals(event1, event2);
    }

    public void testToString() {
        Event event = new Event();
        event.setEventId(1);
        event.setEventType("LOGIN");
        event.setUserId("user123");
        event.setDescription("User logged in successfully");

        String toString = event.toString();
        assertTrue(toString.contains("eventId=1"));
        assertTrue(toString.contains("eventType=LOGIN"));
        assertTrue(toString.contains("userId=user123"));
        assertTrue(toString.contains("description=User logged in successfully"));
    }
}
