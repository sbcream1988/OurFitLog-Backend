package com.ofl.domain.gathering.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ofl.domain.member.entity.Member;
import com.ofl.domain.participation.entity.Participation;
import com.ofl.global.entity.BaseTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "gathering")
public class Gathering extends BaseTime{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	//설명
	private String description;
	
	//모임 시간
	private LocalDateTime startsAt;
	
	//최대 인원
	private int maxCapacity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	private Member host;
	
	@OneToMany(mappedBy = "gathering", cascade = CascadeType.ALL)
	private List<Participation> participations = new ArrayList<>();
	
	public Gathering(String title, String description, LocalDateTime startsAt, int maxCapacity, Member host) {
		this.title = title;
		this.description = description;
		this.startsAt = startsAt;
		this.maxCapacity = maxCapacity;
		this.host = host;
	}
}
