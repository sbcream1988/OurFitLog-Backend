package com.ofl.domain.gathering.dto.response;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GatheringResponseDto {

	private Long id;
	
	private String title;
	
	private String description;
	
	private LocalDateTime startsAt;
	
	private int maxCapacity;
	
	private String hostNickname;
	
	private long currentParticipationsCount;
	
	private LocalDateTime createdAt;

	public void setCurrentParticipationsCount(long currentCount) {
		this.currentParticipationsCount = currentCount;
	}
	
}
 