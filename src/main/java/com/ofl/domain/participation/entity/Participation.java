package com.ofl.domain.participation.entity;

import com.ofl.domain.gathering.entity.Gathering;
import com.ofl.domain.member.entity.Member;
import com.ofl.global.entity.BaseTime;
import com.ofl.global.entity.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "participation")
public class Participation extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gathering_id")
	private Gathering gathering;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Participation(Member member, Gathering gathering, Status status) {
		this.member = member;
		this.gathering = gathering;
		this.status = status;
	}
}
