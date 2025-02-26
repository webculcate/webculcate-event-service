package com.webculcate.eventservicecore.model.dto.general;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.eventservicecore.constant.ServiceExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceExceptionResponse {

    private ServiceExceptionType exceptionType;

    private String message;

    private List<Object> details;

}
