package com.sonrisa.timeappointmentapp.entity.repository;

import com.sonrisa.timeappointmentapp.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT a FROM AppointmentEntity a WHERE a.from >= :fromDate AND a.from < :toDate AND a.to > :fromDate AND a.to <= :toDate")
    Optional<AppointmentEntity> findAppointmentEntityByFromAndAndTo(LocalDateTime fromDate, LocalDateTime toDate);
}
