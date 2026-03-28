package com.ofl.domain.location.dto.request;

import com.ofl.global.entity.ProviderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationRequestDto {

	private ProviderType provider;
	
	private String providerId;
	
	private String placeName;
	
	private Double latitude;
	
	private Double longitude;
	
	private String address;
}
