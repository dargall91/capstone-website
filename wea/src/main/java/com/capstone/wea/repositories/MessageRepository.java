package com.capstone.wea.repositories;

import com.capstone.wea.entities.CMACMessage;
import com.capstone.wea.repositories.projections.MessageDataProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<CMACMessage, Integer> {
    CMACMessage findFirstByExpiresAfter(@Param("now") OffsetDateTime now);
    CMACMessage findByCapIdentifier(String capIdentifier);
    Page<CMACMessage> findAllBySender(String sender, Pageable page);
    CMACMessage findFirstBySender(String sender);
    @Query(nativeQuery = true, value = "call GetMessageData(:sender, :pageNum, :messageNumber, :messageType, " +
            ":fromDate, :toDate, :orderByDate, :orderByDesc)")
    List<MessageDataProjection> getMessageData(String sender, int pageNum, Integer messageNumber,
                                               String messageType, String fromDate, String toDate,
                                               boolean orderByDate, boolean orderByDesc);
}
