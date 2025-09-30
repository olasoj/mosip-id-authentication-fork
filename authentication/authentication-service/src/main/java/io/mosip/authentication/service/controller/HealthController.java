package io.mosip.authentication.service.controller;

import io.mosip.authentication.core.health.dto.HealthDetailsResponse;
import io.mosip.authentication.core.health.dto.HealthStatus;
import io.mosip.authentication.core.spi.health.service.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Tag(name = "Health API", description = "Endpoints for health operation")
public class HealthController {

    private final HealthService healthService;

    public HealthController(@Qualifier("healthServiceImpl") HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping(value = "/details")
    @Operation(summary = "Health Details API", description = "Retrieve health details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Service is UP",
                    content = @Content(schema = @Schema(implementation = HealthDetailsResponse.class))
            ),
            @ApiResponse(
                    responseCode = "503",
                    description = "Service is DOWN",
                    content = @Content(schema = @Schema(implementation = HealthDetailsResponse.class))
            )
    })
    public ResponseEntity<HealthDetailsResponse> sendLink() {

        HealthDetailsResponse healthDetails = healthService.getHealthDetails();
        HttpStatus httpStatus = HealthStatus.DOWN.name().equals(healthDetails.getStatus()) ? HttpStatus.SERVICE_UNAVAILABLE : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(healthDetails);
    }
}
