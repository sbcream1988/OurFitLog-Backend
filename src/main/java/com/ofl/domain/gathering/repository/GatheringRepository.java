package com.ofl.domain.gathering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.gathering.entity.Gathering;

public interface GatheringRepository extends JpaRepository<Gathering, Long>, GatheringRepositoryCustom{

}
