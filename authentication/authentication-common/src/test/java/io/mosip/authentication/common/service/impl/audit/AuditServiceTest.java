package io.mosip.authentication.common.service.impl.audit;

import io.mosip.authentication.core.audit.dto.AuditRequest;
import io.mosip.authentication.core.audit.dto.AuditResponse;
import io.mosip.authentication.core.audit.dto.Event;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class AuditServiceTest extends TestCase {

    private final String input;
    @Mock
    private AuditRepositoryImpl auditRepository;
    @InjectMocks
    private AuditServiceImpl auditService;

    // Constructor for each test case
    public AuditServiceTest(String input) {
        this.input = input;
    }

    // Test data provider
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {""},       // empty string
                {"   "}     // whitespace string
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAudit_Success() {
        // Arrange
        AuditRequest request = new AuditRequest();
        request.setEventType("LOGIN");
        request.setUserId("user123");
        request.setDescription("User logged in");

        Event savedEvent = new Event();
        savedEvent.setEventId(1001);
        when(auditRepository.recordEvent(any(Event.class))).thenReturn(savedEvent);

        // Act
        AuditResponse response = auditService.audit(request);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getTimestamp()); // generated at runtime
        assertEquals("1001", response.getEventId());

        // Verify correct Event was passed to repository
        ArgumentCaptor<Event> captor = ArgumentCaptor.forClass(Event.class);
        verify(auditRepository).recordEvent(captor.capture());
        Event captured = captor.getValue();
        assertEquals("LOGIN", captured.getEventType());
        assertEquals("user123", captured.getUserId());
        assertEquals("User logged in", captured.getDescription());
    }

    @Test
    public void testAudit_NullRequestThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> auditService.audit(null));
    }

    @Test
    public void testAudit_BlankEventTypeThrowsException() {
        AuditRequest request = new AuditRequest();
        request.setEventType(input);
        request.setUserId("user123");

        assertThrows(IllegalArgumentException.class, () -> auditService.audit(request));
    }

    @Test
    public void testAudit_BlankUserIdThrowsException() {
        AuditRequest request = new AuditRequest();
        request.setEventType("LOGIN");
        request.setUserId(input);

        assertThrows(IllegalArgumentException.class, () -> auditService.audit(request));
    }
}
