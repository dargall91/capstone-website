package com.capstone.wea.repositories;

import com.capstone.wea.entities.CMACMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;

public interface MessageRepository extends JpaRepository<CMACMessage, Integer> {
    CMACMessage findFirstByExpiresBefore(@Param("now") OffsetDateTime now);
    CMACMessage findByCapIdentifier(String capIdentifier);
}
