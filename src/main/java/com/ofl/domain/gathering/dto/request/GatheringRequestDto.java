package com.ofl.domain.gathering.dto.request;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatheringRequestDto {


	
	private String title;
	
	private String description;
	
	private LocalDateTime startsAt;
	
	private int maxCapacity;
	

}
