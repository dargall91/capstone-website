package com.capstone.wea.repositories;

import com.capstone.wea.entities.CollectedDeviceData;
import com.capstone.wea.repositories.projections.CollectedStatsProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<CollectedDeviceData, Long> {
    @Query(nativeQuery = true, value = "call GetDeviceStats(:sender, :pageNum, :messageNumber, :messageType, " +
            ":fromDate, :toDate, :orderByDate, :orderByDesc)")
    List<CollectedStatsProjections> getDeviceStats(String sender, int pageNum, Integer messageNumber,
                                                   String messageType, String fromDate, String toDate,
                                                   boolean orderByDate, boolean orderByDesc);
}
