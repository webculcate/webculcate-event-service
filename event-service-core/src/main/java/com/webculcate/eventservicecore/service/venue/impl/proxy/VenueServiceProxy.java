package com.webculcate.eventservicecore.service.venue.impl.proxy;

import com.webculcate.eventservicecore.exception.venue.InvalidVenueCreationRequestException;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationRequest;
import com.webculcate.eventservicecore.model.dto.venue.VenueCreationResponse;
import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import com.webculcate.eventservicecore.service.venue.IVenueService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.webculcate.eventservicecore.constant.ServiceConstant.STRING_SPACE;
import static com.webculcate.eventservicecore.constant.VenueServiceStrategyType.VENUE_SERVICE_PROXY;

@Slf4j
@Service(VENUE_SERVICE_PROXY)
@RequiredArgsConstructor
public class VenueServiceProxy implements IVenueService {

    private final Validator serviceValidator;

    private final IVenueService venueService;

    @Override
    public VenueDto getVenue(Long id) {
        return venueService.getVenue(id);
    }

    @Override
    public VenueCreationResponse createVenue(VenueCreationRequest request) {
        Set<ConstraintViolation<VenueCreationRequest>> validationResults = serviceValidator.validate(request);
        if (!validationResults.isEmpty()) {
            List<String> errorMessageList = validationResults.stream()
                    .map(result -> result.getPropertyPath() + STRING_SPACE + result.getMessage())
                    .toList();
            throw new InvalidVenueCreationRequestException(errorMessageList);
        }
        return venueService.createVenue(request);
    }
}
