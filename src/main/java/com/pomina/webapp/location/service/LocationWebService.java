package com.pomina.webapp.location.service;

import com.pomina.webapp.location.dto.request.LocationRequestCreateDto;
import com.pomina.webapp.location.dto.request.LocationRequestUpdateDto;

public interface LocationWebService {
    int updateLocation(Integer userId, LocationRequestUpdateDto locationRequestUpdateDto);

    int createLocation(LocationRequestCreateDto locationRequestCreateDto);
}
