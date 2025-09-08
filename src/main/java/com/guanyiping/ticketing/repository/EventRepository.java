package com.guanyiping.ticketing.repository;

import com.guanyiping.ticketing.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Events,Long> {
    Optional<Events> findById(Long id);

    @Override
    void deleteById(Long aLong);
}
