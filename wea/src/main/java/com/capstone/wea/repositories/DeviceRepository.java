package com.capstone.wea.repositories;

import com.capstone.wea.entities.CollectedDeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<CollectedDeviceData, Long> {
}
