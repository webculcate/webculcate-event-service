package com.webculcate.eventservicecore.service.venue.impl;

import com.webculcate.eventservicecore.model.dto.venue.VenueDto;
import com.webculcate.eventservicecore.model.entity.Venue;
import com.webculcate.eventservicecore.service.venue.IVenueDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultVenueDtoMapper implements IVenueDtoMapper {

    @Override
    public VenueDto mapToVenueDto(Venue venue) {
        VenueDto venueDto = VenueDto.initializeBlankVenueDto();
        copyProperties(venue, venueDto);
        if (nonNull(venue.getTimeLog()) && nonNull(venueDto.getTimeLog()))
            copyProperties(venue.getTimeLog(), venueDto.getTimeLog());
        return venueDto;
    }

}
