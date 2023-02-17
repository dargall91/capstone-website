package com.capstone.wea.repositories;

import com.capstone.wea.entities.CMACMessage;
import com.capstone.wea.repositories.projections.MessageListProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;

public interface MessageRepository extends JpaRepository<CMACMessage, Integer>,
        PagingAndSortingRepository<CMACMessage, Integer> {
    CMACMessage findFirstByExpiresAfter(@Param("now") OffsetDateTime now);
    CMACMessage findByCapIdentifier(String capIdentifier);
    Page<CMACMessage> findAllBySender(String sender, Pageable page);
}
