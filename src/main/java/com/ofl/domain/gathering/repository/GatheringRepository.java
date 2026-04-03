package com.ofl.domain.gathering.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ofl.domain.gathering.entity.Gathering;


import jakarta.persistence.LockModeType;

public interface GatheringRepository extends JpaRepository<Gathering, Long>, GatheringRepositoryCustom{

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select g from Gathering g where g.id = :id")
	Optional<Gathering> findByIdWithLock(@Param("id" )Long id);
}
