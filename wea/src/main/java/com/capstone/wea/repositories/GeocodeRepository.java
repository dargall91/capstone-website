package com.capstone.wea.repositories;

import com.capstone.wea.entities.Geocode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeocodeRepository extends JpaRepository<Geocode, String> {
}
