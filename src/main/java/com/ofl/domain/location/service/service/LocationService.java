package com.ofl.domain.location.service.service;

import com.ofl.domain.location.dto.response.LocationResponseDto;

public interface LocationService {
	LocationResponseDto getLocationById(Long id);
}
