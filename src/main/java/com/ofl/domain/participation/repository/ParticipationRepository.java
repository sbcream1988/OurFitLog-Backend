package com.ofl.domain.participation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.participation.entity.Participation;

public interface ParticipationRepository extends JpaRepository<Participation, Long>, ParticipationRepositoryCustom{

}
