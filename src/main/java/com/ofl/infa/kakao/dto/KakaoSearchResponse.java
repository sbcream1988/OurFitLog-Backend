package com.ofl.infa.kakao.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoSearchResponse {

	private List<KakaoDocument> documents;
	private KakaoMeta meta;
	
	@Getter
	@Setter
	public static class KakaoDocument{
		private String place_name;
		private String address_name;
		private String road_address_name;
		private String x;
		private String y;
		private String distance;
		
	}
	
	@Getter
	@Setter
	public static class KakaoMeta{
		private Integer total_count;
		private Boolean is_end;
	}
}
