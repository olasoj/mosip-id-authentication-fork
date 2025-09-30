package io.mosip.authentication.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.authentication.core.audit.dto.AuditRequest;
import io.mosip.authentication.core.audit.dto.AuditResponse;
import io.mosip.authentication.core.spi.audit.service.AuditService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuditController.class)
public class AuditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuditService auditService;

    @Test
//    @Ignore("Revisit error")
    public void testAudit_Success() throws Exception {
        AuditResponse response = new AuditResponse();
        response.setEventId("123");
        response.setTimestamp("2025-09-30T10:15:30Z");

        when(auditService.audit(any(AuditRequest.class)))
                .thenReturn(response);

        AuditRequest request = new AuditRequest();
        request.setEventType("LOGIN");
        request.setUserId("user123");
        request.setDescription("User logged in");

        mockMvc.perform(post("/api/v1/audit/log")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").value("123"))
                .andExpect(jsonPath("$.timestamp").value("2025-09-30T10:15:30Z"));
    }

    @Test
    @Ignore("Revisit error")
    public void testAudit_ValidationFailure() throws Exception {
        // Missing userId → invalid
        AuditRequest invalidRequest = new AuditRequest();
        invalidRequest.setEventType("LOGIN");

        mockMvc.perform(post("/api/v1/audit/log")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}

