package io.ulysse.ulysseIndepBack.repositories;

import io.ulysse.ulysseIndepBack.entities.Entities.EntitiesUserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends JpaRepository<EntitiesUserHistory, Integer> {
}