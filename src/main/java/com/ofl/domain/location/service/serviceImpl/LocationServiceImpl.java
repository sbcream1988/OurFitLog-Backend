package com.ofl.domain.location.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ofl.domain.location.dto.response.LocationResponseDto;
import com.ofl.domain.location.entity.Location;
import com.ofl.domain.location.mapper.LocationMapper;
import com.ofl.domain.location.repository.LocationRepository;
import com.ofl.domain.location.service.service.LocationService;
import com.ofl.global.error.CustomException;
import com.ofl.global.error.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationServiceImpl implements LocationService{

	private final LocationRepository locationRepository;
	private final LocationMapper locationMapper;
	
	@Override
	public LocationResponseDto getLocationById(Long id) {
		Location location = locationRepository.findById(id)
				.orElseThrow(()-> new CustomException(ErrorCode.LOCATION_NOT_FOUND));
		
		return locationMapper.toDto(location);
	}
}
