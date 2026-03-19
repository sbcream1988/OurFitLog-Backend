package com.ofl.domain.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.location.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

}
